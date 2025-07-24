package com.tegonal.minimalist.java.generators;

import com.tegonal.minimalist.config.ComponentFactoryContainer;
import com.tegonal.minimalist.config.ComponentFactoryContainerProvider;
import com.tegonal.minimalist.generators.OrderedArgsGeneratorCombineKt;
import kotlin.Pair;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

class KotlinBasedOrderedArgsGenerator<A1> implements OrderedArgsGenerator<A1>, ComponentFactoryContainerProvider {
	private com.tegonal.minimalist.generators.OrderedArgsGenerator<A1> generator;

	public KotlinBasedOrderedArgsGenerator(com.tegonal.minimalist.generators.OrderedArgsGenerator<A1> generator) {
		this.generator = generator;
	}

	@Override
	public <A2> OrderedArgsGenerator<Pair<A1, A2>> combine(OrderedArgsGenerator<A2> other) {
		return OrderedArgsGenerator.fromKotlin(OrderedArgsGeneratorCombineKt.combineToTuple2(this, other));
	}

	@Override
	public int getSize() {
		return generator.getSize();
	}

	@Override
	public @NotNull Sequence<A1> generate(int offset) {
		return generator.generate(offset);
	}

	@Override
	public @NotNull ComponentFactoryContainer getComponentFactoryContainer() {
		return ((ComponentFactoryContainerProvider) generator).getComponentFactoryContainer();
	}
	// You must implement all methods from the OrderedArgsGenerator<A1> interface here
}

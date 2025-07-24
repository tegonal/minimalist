package com.tegonal.minimalist.java.generators;

import kotlin.Pair;

public interface OrderedArgsGenerator<A1> extends com.tegonal.minimalist.generators.OrderedArgsGenerator<A1> {

	<A2> OrderedArgsGenerator<Pair<A1, A2>> combine(OrderedArgsGenerator<A2> other);

	static <A1> OrderedArgsGenerator<A1> fromKotlin(com.tegonal.minimalist.generators.OrderedArgsGenerator<A1> generator) {
		return new KotlinBasedOrderedArgsGenerator<>(generator);
	}
}

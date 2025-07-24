package com.tegonal.minimalist.java;

import com.tegonal.minimalist.java.generators.OrderedArgsGenerator;
import com.tegonal.minimalist.generators.OrderedExtensionPoint;
import kotlin.ranges.IntRange;

import static com.tegonal.minimalist.generators.GeneratorFactoryExtensionPointsKt.getOrdered;
import static com.tegonal.minimalist.generators.OrderedArgsGeneratorFromRangeKt.fromRange;

public class OrderedDsl {
	private OrderedExtensionPoint _ordered;

	OrderedDsl(OrderedExtensionPoint extensionPoint) {
		_ordered = extensionPoint;
	}

	public OrderedArgsGenerator<Integer> intFromTo(Integer from, Integer toInclusive) {
		return OrderedArgsGenerator.fromKotlin(fromRange(_ordered, new IntRange(from, toInclusive)));
	}

	public static OrderedDsl ordered = new OrderedDsl(getOrdered());
}

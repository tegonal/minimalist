package com.tegonal.minimalist.java;

import com.tegonal.minimalist.generators.OrderedArgsGenerator;
import com.tegonal.minimalist.java.providers.ArgsSource;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.params.ParameterizedTest;

import static ch.tutteli.kbox.TupleFactoryKt.Tuple;
import static com.tegonal.minimalist.java.OrderedDsl.ordered;

class SimpleTest {

	@ParameterizedTest
	@ArgsSource(methodName = "ints")
	void oneInt(int i1) {
		System.out.println(i1);
	}

	@ParameterizedTest
	@ArgsSource(methodName = "twoInts")
	void twoInts(int i1, int i2) {
		System.out.println(i1 + " / " + i2);
	}

	@ParameterizedTest
	@ArgsSource(methodName = "twoIntsAsTuple")
	void twoIntsFromTuple(int i1, int i2) {
		System.out.println(i1 + " / " + i2);
	}

	static OrderedArgsGenerator<Integer> ints() {
		return ordered.intFromTo(1, 5);
	}

	static OrderedArgsGenerator<Pair<Integer, Integer>> twoInts() {
		var generator1 = ordered.intFromTo(1, 2);
		var generator2 = ordered.intFromTo(1, 2);
		return generator1.combine(generator2);
	}

	static @NotNull Pair<OrderedArgsGenerator<Integer>, OrderedArgsGenerator<Integer>> twoIntsAsTuple() {
		return Tuple(ordered.intFromTo(1, 2), ordered.intFromTo(1, 2));
	}
}

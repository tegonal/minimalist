// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generateTestJava
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.variist.java.arguments.drop;

import ch.tutteli.atrium.api.fluent.en_GB.*;
import ch.tutteli.atrium.creating.Expect;
import com.tegonal.variist.*;
import com.tegonal.variist.testutils.atrium.*;
import com.tegonal.variist.java.*;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import static ch.tutteli.atrium.api.fluent.en_GB.AnyExpectationsKt.*;
import static ch.tutteli.atrium.api.fluent.en_GB.ArraySubjectChangersKt.*;
import static ch.tutteli.atrium.api.fluent.en_GB.IterableExpectationsKt.*;
import static ch.tutteli.atrium.api.verbs.ExpectKt.expect;
import static com.tegonal.variist.testutils.atrium.Args1ExpectationsKt.*;
import static com.tegonal.variist.testutils.atrium.Args2ExpectationsKt.*;
import static com.tegonal.variist.testutils.atrium.Args3ExpectationsKt.*;
import static com.tegonal.variist.testutils.atrium.Args4ExpectationsKt.*;
import static com.tegonal.variist.testutils.atrium.Args5ExpectationsKt.*;
import static com.tegonal.variist.testutils.atrium.Args6ExpectationsKt.*;
import static com.tegonal.variist.testutils.atrium.Args7ExpectationsKt.*;
import static com.tegonal.variist.testutils.atrium.Args8ExpectationsKt.*;
import static com.tegonal.variist.testutils.atrium.Args9ExpectationsKt.*;
import static com.tegonal.variist.testutils.atrium.Args10ExpectationsKt.*;

public class Args10DropTest {

	@Test
	public void dropArg1() {
		var args = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			(short) 1,
			(byte) 2,
			BigInteger.valueOf(3),
			new Representation("rep 1"),
			"rep 2",
			"rep 3",
			"rep 4",
			"rep 5",
			"rep 6",
			"rep 7",
			"rep 8",
			"rep 9",
			"rep 10"
		);
		Args9<Long, Float, Double, Character, String, LocalDate, Short, Byte, BigInteger> argsResult = args.dropArg1();
		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA2());
			toEqual(getA2(e), args.getA3());
			toEqual(getA3(e), args.getA4());
			toEqual(getA4(e), args.getA5());
			toEqual(getA5(e), args.getA6());
			toEqual(getA6(e), args.getA7());
			toEqual(getA7(e), args.getA8());
			toEqual(getA8(e), args.getA9());
			toEqual(getA9(e), args.getA10());
			toEqual(getRepresentation1(e), args.getRepresentation2());
			toEqual(getRepresentation2(e), args.getRepresentation3());
			toEqual(getRepresentation3(e), args.getRepresentation4());
			toEqual(getRepresentation4(e), args.getRepresentation5());
			toEqual(getRepresentation5(e), args.getRepresentation6());
			toEqual(getRepresentation6(e), args.getRepresentation7());
			toEqual(getRepresentation7(e), args.getRepresentation8());
			toEqual(getRepresentation8(e), args.getRepresentation9());
			toEqual(getRepresentation9(e), args.getRepresentation10());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void dropArg2() {
		var args = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			(short) 1,
			(byte) 2,
			BigInteger.valueOf(3),
			new Representation("rep 1"),
			"rep 2",
			"rep 3",
			"rep 4",
			"rep 5",
			"rep 6",
			"rep 7",
			"rep 8",
			"rep 9",
			"rep 10"
		);
		Args9<Integer, Float, Double, Character, String, LocalDate, Short, Byte, BigInteger> argsResult = args.dropArg2();
		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA3());
			toEqual(getA3(e), args.getA4());
			toEqual(getA4(e), args.getA5());
			toEqual(getA5(e), args.getA6());
			toEqual(getA6(e), args.getA7());
			toEqual(getA7(e), args.getA8());
			toEqual(getA8(e), args.getA9());
			toEqual(getA9(e), args.getA10());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation3());
			toEqual(getRepresentation3(e), args.getRepresentation4());
			toEqual(getRepresentation4(e), args.getRepresentation5());
			toEqual(getRepresentation5(e), args.getRepresentation6());
			toEqual(getRepresentation6(e), args.getRepresentation7());
			toEqual(getRepresentation7(e), args.getRepresentation8());
			toEqual(getRepresentation8(e), args.getRepresentation9());
			toEqual(getRepresentation9(e), args.getRepresentation10());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void dropArg3() {
		var args = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			(short) 1,
			(byte) 2,
			BigInteger.valueOf(3),
			new Representation("rep 1"),
			"rep 2",
			"rep 3",
			"rep 4",
			"rep 5",
			"rep 6",
			"rep 7",
			"rep 8",
			"rep 9",
			"rep 10"
		);
		Args9<Integer, Long, Double, Character, String, LocalDate, Short, Byte, BigInteger> argsResult = args.dropArg3();
		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA4());
			toEqual(getA4(e), args.getA5());
			toEqual(getA5(e), args.getA6());
			toEqual(getA6(e), args.getA7());
			toEqual(getA7(e), args.getA8());
			toEqual(getA8(e), args.getA9());
			toEqual(getA9(e), args.getA10());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation4());
			toEqual(getRepresentation4(e), args.getRepresentation5());
			toEqual(getRepresentation5(e), args.getRepresentation6());
			toEqual(getRepresentation6(e), args.getRepresentation7());
			toEqual(getRepresentation7(e), args.getRepresentation8());
			toEqual(getRepresentation8(e), args.getRepresentation9());
			toEqual(getRepresentation9(e), args.getRepresentation10());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void dropArg4() {
		var args = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			(short) 1,
			(byte) 2,
			BigInteger.valueOf(3),
			new Representation("rep 1"),
			"rep 2",
			"rep 3",
			"rep 4",
			"rep 5",
			"rep 6",
			"rep 7",
			"rep 8",
			"rep 9",
			"rep 10"
		);
		Args9<Integer, Long, Float, Character, String, LocalDate, Short, Byte, BigInteger> argsResult = args.dropArg4();
		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), args.getA5());
			toEqual(getA5(e), args.getA6());
			toEqual(getA6(e), args.getA7());
			toEqual(getA7(e), args.getA8());
			toEqual(getA8(e), args.getA9());
			toEqual(getA9(e), args.getA10());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), args.getRepresentation5());
			toEqual(getRepresentation5(e), args.getRepresentation6());
			toEqual(getRepresentation6(e), args.getRepresentation7());
			toEqual(getRepresentation7(e), args.getRepresentation8());
			toEqual(getRepresentation8(e), args.getRepresentation9());
			toEqual(getRepresentation9(e), args.getRepresentation10());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void dropArg5() {
		var args = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			(short) 1,
			(byte) 2,
			BigInteger.valueOf(3),
			new Representation("rep 1"),
			"rep 2",
			"rep 3",
			"rep 4",
			"rep 5",
			"rep 6",
			"rep 7",
			"rep 8",
			"rep 9",
			"rep 10"
		);
		Args9<Integer, Long, Float, Double, String, LocalDate, Short, Byte, BigInteger> argsResult = args.dropArg5();
		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), args.getA6());
			toEqual(getA6(e), args.getA7());
			toEqual(getA7(e), args.getA8());
			toEqual(getA8(e), args.getA9());
			toEqual(getA9(e), args.getA10());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), args.getRepresentation6());
			toEqual(getRepresentation6(e), args.getRepresentation7());
			toEqual(getRepresentation7(e), args.getRepresentation8());
			toEqual(getRepresentation8(e), args.getRepresentation9());
			toEqual(getRepresentation9(e), args.getRepresentation10());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void dropArg6() {
		var args = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			(short) 1,
			(byte) 2,
			BigInteger.valueOf(3),
			new Representation("rep 1"),
			"rep 2",
			"rep 3",
			"rep 4",
			"rep 5",
			"rep 6",
			"rep 7",
			"rep 8",
			"rep 9",
			"rep 10"
		);
		Args9<Integer, Long, Float, Double, Character, LocalDate, Short, Byte, BigInteger> argsResult = args.dropArg6();
		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), args.getA5());
			toEqual(getA6(e), args.getA7());
			toEqual(getA7(e), args.getA8());
			toEqual(getA8(e), args.getA9());
			toEqual(getA9(e), args.getA10());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), args.getRepresentation5());
			toEqual(getRepresentation6(e), args.getRepresentation7());
			toEqual(getRepresentation7(e), args.getRepresentation8());
			toEqual(getRepresentation8(e), args.getRepresentation9());
			toEqual(getRepresentation9(e), args.getRepresentation10());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void dropArg7() {
		var args = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			(short) 1,
			(byte) 2,
			BigInteger.valueOf(3),
			new Representation("rep 1"),
			"rep 2",
			"rep 3",
			"rep 4",
			"rep 5",
			"rep 6",
			"rep 7",
			"rep 8",
			"rep 9",
			"rep 10"
		);
		Args9<Integer, Long, Float, Double, Character, String, Short, Byte, BigInteger> argsResult = args.dropArg7();
		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), args.getA5());
			toEqual(getA6(e), args.getA6());
			toEqual(getA7(e), args.getA8());
			toEqual(getA8(e), args.getA9());
			toEqual(getA9(e), args.getA10());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), args.getRepresentation5());
			toEqual(getRepresentation6(e), args.getRepresentation6());
			toEqual(getRepresentation7(e), args.getRepresentation8());
			toEqual(getRepresentation8(e), args.getRepresentation9());
			toEqual(getRepresentation9(e), args.getRepresentation10());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void dropArg8() {
		var args = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			(short) 1,
			(byte) 2,
			BigInteger.valueOf(3),
			new Representation("rep 1"),
			"rep 2",
			"rep 3",
			"rep 4",
			"rep 5",
			"rep 6",
			"rep 7",
			"rep 8",
			"rep 9",
			"rep 10"
		);
		Args9<Integer, Long, Float, Double, Character, String, LocalDate, Byte, BigInteger> argsResult = args.dropArg8();
		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), args.getA5());
			toEqual(getA6(e), args.getA6());
			toEqual(getA7(e), args.getA7());
			toEqual(getA8(e), args.getA9());
			toEqual(getA9(e), args.getA10());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), args.getRepresentation5());
			toEqual(getRepresentation6(e), args.getRepresentation6());
			toEqual(getRepresentation7(e), args.getRepresentation7());
			toEqual(getRepresentation8(e), args.getRepresentation9());
			toEqual(getRepresentation9(e), args.getRepresentation10());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void dropArg9() {
		var args = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			(short) 1,
			(byte) 2,
			BigInteger.valueOf(3),
			new Representation("rep 1"),
			"rep 2",
			"rep 3",
			"rep 4",
			"rep 5",
			"rep 6",
			"rep 7",
			"rep 8",
			"rep 9",
			"rep 10"
		);
		Args9<Integer, Long, Float, Double, Character, String, LocalDate, Short, BigInteger> argsResult = args.dropArg9();
		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), args.getA5());
			toEqual(getA6(e), args.getA6());
			toEqual(getA7(e), args.getA7());
			toEqual(getA8(e), args.getA8());
			toEqual(getA9(e), args.getA10());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), args.getRepresentation5());
			toEqual(getRepresentation6(e), args.getRepresentation6());
			toEqual(getRepresentation7(e), args.getRepresentation7());
			toEqual(getRepresentation8(e), args.getRepresentation8());
			toEqual(getRepresentation9(e), args.getRepresentation10());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void dropArg10() {
		var args = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			(short) 1,
			(byte) 2,
			BigInteger.valueOf(3),
			new Representation("rep 1"),
			"rep 2",
			"rep 3",
			"rep 4",
			"rep 5",
			"rep 6",
			"rep 7",
			"rep 8",
			"rep 9",
			"rep 10"
		);
		Args9<Integer, Long, Float, Double, Character, String, LocalDate, Short, Byte> argsResult = args.dropArg10();
		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), args.getA5());
			toEqual(getA6(e), args.getA6());
			toEqual(getA7(e), args.getA7());
			toEqual(getA8(e), args.getA8());
			toEqual(getA9(e), args.getA9());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), args.getRepresentation5());
			toEqual(getRepresentation6(e), args.getRepresentation6());
			toEqual(getRepresentation7(e), args.getRepresentation7());
			toEqual(getRepresentation8(e), args.getRepresentation8());
			toEqual(getRepresentation9(e), args.getRepresentation9());
			return Unit.INSTANCE;
		});
	}

}

// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generateTestJava
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.variist.java.arguments.append;

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

public class Args1AppendTest {

	@Test
	public void append_Args1() {
		var firstArgs = Args.of(
			1,
			new Representation("rep 1")
		);
		var secondArgs = Args.of(
			2L,
			new Representation("rep 1")
		);
		var argsResult = firstArgs.append(secondArgs);
		expect(argsResult, e -> {
			toEqual(getA1(e), firstArgs.getA1());
			toEqual(getRepresentation1(e), firstArgs.getRepresentation1());
			toEqual(getA2(e), secondArgs.getA1());
			toEqual(getRepresentation2(e), secondArgs.getRepresentation1());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void append_Args2() {
		var firstArgs = Args.of(
			1,
			new Representation("rep 1")
		);
		var secondArgs = Args.of(
			2L,
			3F,
			new Representation("rep 1"),
			"rep 2"
		);
		var argsResult = firstArgs.append(secondArgs);
		expect(argsResult, e -> {
			toEqual(getA1(e), firstArgs.getA1());
			toEqual(getRepresentation1(e), firstArgs.getRepresentation1());
			toEqual(getA2(e), secondArgs.getA1());
			toEqual(getA3(e), secondArgs.getA2());
			toEqual(getRepresentation2(e), secondArgs.getRepresentation1());
			toEqual(getRepresentation3(e), secondArgs.getRepresentation2());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void append_Args3() {
		var firstArgs = Args.of(
			1,
			new Representation("rep 1")
		);
		var secondArgs = Args.of(
			2L,
			3F,
			4.0,
			new Representation("rep 1"),
			"rep 2",
			"rep 3"
		);
		var argsResult = firstArgs.append(secondArgs);
		expect(argsResult, e -> {
			toEqual(getA1(e), firstArgs.getA1());
			toEqual(getRepresentation1(e), firstArgs.getRepresentation1());
			toEqual(getA2(e), secondArgs.getA1());
			toEqual(getA3(e), secondArgs.getA2());
			toEqual(getA4(e), secondArgs.getA3());
			toEqual(getRepresentation2(e), secondArgs.getRepresentation1());
			toEqual(getRepresentation3(e), secondArgs.getRepresentation2());
			toEqual(getRepresentation4(e), secondArgs.getRepresentation3());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void append_Args4() {
		var firstArgs = Args.of(
			1,
			new Representation("rep 1")
		);
		var secondArgs = Args.of(
			2L,
			3F,
			4.0,
			'c',
			new Representation("rep 1"),
			"rep 2",
			"rep 3",
			"rep 4"
		);
		var argsResult = firstArgs.append(secondArgs);
		expect(argsResult, e -> {
			toEqual(getA1(e), firstArgs.getA1());
			toEqual(getRepresentation1(e), firstArgs.getRepresentation1());
			toEqual(getA2(e), secondArgs.getA1());
			toEqual(getA3(e), secondArgs.getA2());
			toEqual(getA4(e), secondArgs.getA3());
			toEqual(getA5(e), secondArgs.getA4());
			toEqual(getRepresentation2(e), secondArgs.getRepresentation1());
			toEqual(getRepresentation3(e), secondArgs.getRepresentation2());
			toEqual(getRepresentation4(e), secondArgs.getRepresentation3());
			toEqual(getRepresentation5(e), secondArgs.getRepresentation4());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void append_Args5() {
		var firstArgs = Args.of(
			1,
			new Representation("rep 1")
		);
		var secondArgs = Args.of(
			2L,
			3F,
			4.0,
			'c',
			"string",
			new Representation("rep 1"),
			"rep 2",
			"rep 3",
			"rep 4",
			"rep 5"
		);
		var argsResult = firstArgs.append(secondArgs);
		expect(argsResult, e -> {
			toEqual(getA1(e), firstArgs.getA1());
			toEqual(getRepresentation1(e), firstArgs.getRepresentation1());
			toEqual(getA2(e), secondArgs.getA1());
			toEqual(getA3(e), secondArgs.getA2());
			toEqual(getA4(e), secondArgs.getA3());
			toEqual(getA5(e), secondArgs.getA4());
			toEqual(getA6(e), secondArgs.getA5());
			toEqual(getRepresentation2(e), secondArgs.getRepresentation1());
			toEqual(getRepresentation3(e), secondArgs.getRepresentation2());
			toEqual(getRepresentation4(e), secondArgs.getRepresentation3());
			toEqual(getRepresentation5(e), secondArgs.getRepresentation4());
			toEqual(getRepresentation6(e), secondArgs.getRepresentation5());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void append_Args6() {
		var firstArgs = Args.of(
			1,
			new Representation("rep 1")
		);
		var secondArgs = Args.of(
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			new Representation("rep 1"),
			"rep 2",
			"rep 3",
			"rep 4",
			"rep 5",
			"rep 6"
		);
		var argsResult = firstArgs.append(secondArgs);
		expect(argsResult, e -> {
			toEqual(getA1(e), firstArgs.getA1());
			toEqual(getRepresentation1(e), firstArgs.getRepresentation1());
			toEqual(getA2(e), secondArgs.getA1());
			toEqual(getA3(e), secondArgs.getA2());
			toEqual(getA4(e), secondArgs.getA3());
			toEqual(getA5(e), secondArgs.getA4());
			toEqual(getA6(e), secondArgs.getA5());
			toEqual(getA7(e), secondArgs.getA6());
			toEqual(getRepresentation2(e), secondArgs.getRepresentation1());
			toEqual(getRepresentation3(e), secondArgs.getRepresentation2());
			toEqual(getRepresentation4(e), secondArgs.getRepresentation3());
			toEqual(getRepresentation5(e), secondArgs.getRepresentation4());
			toEqual(getRepresentation6(e), secondArgs.getRepresentation5());
			toEqual(getRepresentation7(e), secondArgs.getRepresentation6());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void append_Args7() {
		var firstArgs = Args.of(
			1,
			new Representation("rep 1")
		);
		var secondArgs = Args.of(
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			(short) 1,
			new Representation("rep 1"),
			"rep 2",
			"rep 3",
			"rep 4",
			"rep 5",
			"rep 6",
			"rep 7"
		);
		var argsResult = firstArgs.append(secondArgs);
		expect(argsResult, e -> {
			toEqual(getA1(e), firstArgs.getA1());
			toEqual(getRepresentation1(e), firstArgs.getRepresentation1());
			toEqual(getA2(e), secondArgs.getA1());
			toEqual(getA3(e), secondArgs.getA2());
			toEqual(getA4(e), secondArgs.getA3());
			toEqual(getA5(e), secondArgs.getA4());
			toEqual(getA6(e), secondArgs.getA5());
			toEqual(getA7(e), secondArgs.getA6());
			toEqual(getA8(e), secondArgs.getA7());
			toEqual(getRepresentation2(e), secondArgs.getRepresentation1());
			toEqual(getRepresentation3(e), secondArgs.getRepresentation2());
			toEqual(getRepresentation4(e), secondArgs.getRepresentation3());
			toEqual(getRepresentation5(e), secondArgs.getRepresentation4());
			toEqual(getRepresentation6(e), secondArgs.getRepresentation5());
			toEqual(getRepresentation7(e), secondArgs.getRepresentation6());
			toEqual(getRepresentation8(e), secondArgs.getRepresentation7());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void append_Args8() {
		var firstArgs = Args.of(
			1,
			new Representation("rep 1")
		);
		var secondArgs = Args.of(
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			(short) 1,
			(byte) 2,
			new Representation("rep 1"),
			"rep 2",
			"rep 3",
			"rep 4",
			"rep 5",
			"rep 6",
			"rep 7",
			"rep 8"
		);
		var argsResult = firstArgs.append(secondArgs);
		expect(argsResult, e -> {
			toEqual(getA1(e), firstArgs.getA1());
			toEqual(getRepresentation1(e), firstArgs.getRepresentation1());
			toEqual(getA2(e), secondArgs.getA1());
			toEqual(getA3(e), secondArgs.getA2());
			toEqual(getA4(e), secondArgs.getA3());
			toEqual(getA5(e), secondArgs.getA4());
			toEqual(getA6(e), secondArgs.getA5());
			toEqual(getA7(e), secondArgs.getA6());
			toEqual(getA8(e), secondArgs.getA7());
			toEqual(getA9(e), secondArgs.getA8());
			toEqual(getRepresentation2(e), secondArgs.getRepresentation1());
			toEqual(getRepresentation3(e), secondArgs.getRepresentation2());
			toEqual(getRepresentation4(e), secondArgs.getRepresentation3());
			toEqual(getRepresentation5(e), secondArgs.getRepresentation4());
			toEqual(getRepresentation6(e), secondArgs.getRepresentation5());
			toEqual(getRepresentation7(e), secondArgs.getRepresentation6());
			toEqual(getRepresentation8(e), secondArgs.getRepresentation7());
			toEqual(getRepresentation9(e), secondArgs.getRepresentation8());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void append_Args9() {
		var firstArgs = Args.of(
			1,
			new Representation("rep 1")
		);
		var secondArgs = Args.of(
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
			"rep 9"
		);
		var argsResult = firstArgs.append(secondArgs);
		expect(argsResult, e -> {
			toEqual(getA1(e), firstArgs.getA1());
			toEqual(getRepresentation1(e), firstArgs.getRepresentation1());
			toEqual(getA2(e), secondArgs.getA1());
			toEqual(getA3(e), secondArgs.getA2());
			toEqual(getA4(e), secondArgs.getA3());
			toEqual(getA5(e), secondArgs.getA4());
			toEqual(getA6(e), secondArgs.getA5());
			toEqual(getA7(e), secondArgs.getA6());
			toEqual(getA8(e), secondArgs.getA7());
			toEqual(getA9(e), secondArgs.getA8());
			toEqual(getA10(e), secondArgs.getA9());
			toEqual(getRepresentation2(e), secondArgs.getRepresentation1());
			toEqual(getRepresentation3(e), secondArgs.getRepresentation2());
			toEqual(getRepresentation4(e), secondArgs.getRepresentation3());
			toEqual(getRepresentation5(e), secondArgs.getRepresentation4());
			toEqual(getRepresentation6(e), secondArgs.getRepresentation5());
			toEqual(getRepresentation7(e), secondArgs.getRepresentation6());
			toEqual(getRepresentation8(e), secondArgs.getRepresentation7());
			toEqual(getRepresentation9(e), secondArgs.getRepresentation8());
			toEqual(getRepresentation10(e), secondArgs.getRepresentation9());
			return Unit.INSTANCE;
		});
	}

}

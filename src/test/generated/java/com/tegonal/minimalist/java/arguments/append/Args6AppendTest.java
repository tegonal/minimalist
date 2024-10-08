// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generateTestJava
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.minimalist.java.arguments.append;

import ch.tutteli.atrium.api.fluent.en_GB.*;
import ch.tutteli.atrium.creating.Expect;
import com.tegonal.minimalist.*;
import com.tegonal.minimalist.atrium.*;
import com.tegonal.minimalist.java.*;
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
import static com.tegonal.minimalist.atrium.Args1ExpectationsKt.*;
import static com.tegonal.minimalist.atrium.Args2ExpectationsKt.*;
import static com.tegonal.minimalist.atrium.Args3ExpectationsKt.*;
import static com.tegonal.minimalist.atrium.Args4ExpectationsKt.*;
import static com.tegonal.minimalist.atrium.Args5ExpectationsKt.*;
import static com.tegonal.minimalist.atrium.Args6ExpectationsKt.*;
import static com.tegonal.minimalist.atrium.Args7ExpectationsKt.*;
import static com.tegonal.minimalist.atrium.Args8ExpectationsKt.*;
import static com.tegonal.minimalist.atrium.Args9ExpectationsKt.*;
import static com.tegonal.minimalist.atrium.Args10ExpectationsKt.*;

public class Args6AppendTest {

	@Test
	public void append_Args1() {
		var firstArgs = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			new Representation("rep 1"),
			"rep 2",
			"rep 3",
			"rep 4",
			"rep 5",
			"rep 6"
		);
		var secondArgs = Args.of(
			LocalDate.now(),
			new Representation("rep 1")
		);
		var argsResult = firstArgs.append(secondArgs);
		expect(argsResult, e -> {
			toEqual(getA1(e), firstArgs.getA1());
			toEqual(getA2(e), firstArgs.getA2());
			toEqual(getA3(e), firstArgs.getA3());
			toEqual(getA4(e), firstArgs.getA4());
			toEqual(getA5(e), firstArgs.getA5());
			toEqual(getA6(e), firstArgs.getA6());
			toEqual(getRepresentation1(e), firstArgs.getRepresentation1());
			toEqual(getRepresentation2(e), firstArgs.getRepresentation2());
			toEqual(getRepresentation3(e), firstArgs.getRepresentation3());
			toEqual(getRepresentation4(e), firstArgs.getRepresentation4());
			toEqual(getRepresentation5(e), firstArgs.getRepresentation5());
			toEqual(getRepresentation6(e), firstArgs.getRepresentation6());
			toEqual(getA7(e), secondArgs.getA1());
			toEqual(getRepresentation7(e), secondArgs.getRepresentation1());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void append_Args2() {
		var firstArgs = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			new Representation("rep 1"),
			"rep 2",
			"rep 3",
			"rep 4",
			"rep 5",
			"rep 6"
		);
		var secondArgs = Args.of(
			LocalDate.now(),
			(short) 1,
			new Representation("rep 1"),
			"rep 2"
		);
		var argsResult = firstArgs.append(secondArgs);
		expect(argsResult, e -> {
			toEqual(getA1(e), firstArgs.getA1());
			toEqual(getA2(e), firstArgs.getA2());
			toEqual(getA3(e), firstArgs.getA3());
			toEqual(getA4(e), firstArgs.getA4());
			toEqual(getA5(e), firstArgs.getA5());
			toEqual(getA6(e), firstArgs.getA6());
			toEqual(getRepresentation1(e), firstArgs.getRepresentation1());
			toEqual(getRepresentation2(e), firstArgs.getRepresentation2());
			toEqual(getRepresentation3(e), firstArgs.getRepresentation3());
			toEqual(getRepresentation4(e), firstArgs.getRepresentation4());
			toEqual(getRepresentation5(e), firstArgs.getRepresentation5());
			toEqual(getRepresentation6(e), firstArgs.getRepresentation6());
			toEqual(getA7(e), secondArgs.getA1());
			toEqual(getA8(e), secondArgs.getA2());
			toEqual(getRepresentation7(e), secondArgs.getRepresentation1());
			toEqual(getRepresentation8(e), secondArgs.getRepresentation2());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void append_Args3() {
		var firstArgs = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			new Representation("rep 1"),
			"rep 2",
			"rep 3",
			"rep 4",
			"rep 5",
			"rep 6"
		);
		var secondArgs = Args.of(
			LocalDate.now(),
			(short) 1,
			(byte) 2,
			new Representation("rep 1"),
			"rep 2",
			"rep 3"
		);
		var argsResult = firstArgs.append(secondArgs);
		expect(argsResult, e -> {
			toEqual(getA1(e), firstArgs.getA1());
			toEqual(getA2(e), firstArgs.getA2());
			toEqual(getA3(e), firstArgs.getA3());
			toEqual(getA4(e), firstArgs.getA4());
			toEqual(getA5(e), firstArgs.getA5());
			toEqual(getA6(e), firstArgs.getA6());
			toEqual(getRepresentation1(e), firstArgs.getRepresentation1());
			toEqual(getRepresentation2(e), firstArgs.getRepresentation2());
			toEqual(getRepresentation3(e), firstArgs.getRepresentation3());
			toEqual(getRepresentation4(e), firstArgs.getRepresentation4());
			toEqual(getRepresentation5(e), firstArgs.getRepresentation5());
			toEqual(getRepresentation6(e), firstArgs.getRepresentation6());
			toEqual(getA7(e), secondArgs.getA1());
			toEqual(getA8(e), secondArgs.getA2());
			toEqual(getA9(e), secondArgs.getA3());
			toEqual(getRepresentation7(e), secondArgs.getRepresentation1());
			toEqual(getRepresentation8(e), secondArgs.getRepresentation2());
			toEqual(getRepresentation9(e), secondArgs.getRepresentation3());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void append_Args4() {
		var firstArgs = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			new Representation("rep 1"),
			"rep 2",
			"rep 3",
			"rep 4",
			"rep 5",
			"rep 6"
		);
		var secondArgs = Args.of(
			LocalDate.now(),
			(short) 1,
			(byte) 2,
			BigInteger.valueOf(3),
			new Representation("rep 1"),
			"rep 2",
			"rep 3",
			"rep 4"
		);
		var argsResult = firstArgs.append(secondArgs);
		expect(argsResult, e -> {
			toEqual(getA1(e), firstArgs.getA1());
			toEqual(getA2(e), firstArgs.getA2());
			toEqual(getA3(e), firstArgs.getA3());
			toEqual(getA4(e), firstArgs.getA4());
			toEqual(getA5(e), firstArgs.getA5());
			toEqual(getA6(e), firstArgs.getA6());
			toEqual(getRepresentation1(e), firstArgs.getRepresentation1());
			toEqual(getRepresentation2(e), firstArgs.getRepresentation2());
			toEqual(getRepresentation3(e), firstArgs.getRepresentation3());
			toEqual(getRepresentation4(e), firstArgs.getRepresentation4());
			toEqual(getRepresentation5(e), firstArgs.getRepresentation5());
			toEqual(getRepresentation6(e), firstArgs.getRepresentation6());
			toEqual(getA7(e), secondArgs.getA1());
			toEqual(getA8(e), secondArgs.getA2());
			toEqual(getA9(e), secondArgs.getA3());
			toEqual(getA10(e), secondArgs.getA4());
			toEqual(getRepresentation7(e), secondArgs.getRepresentation1());
			toEqual(getRepresentation8(e), secondArgs.getRepresentation2());
			toEqual(getRepresentation9(e), secondArgs.getRepresentation3());
			toEqual(getRepresentation10(e), secondArgs.getRepresentation4());
			return Unit.INSTANCE;
		});
	}

}
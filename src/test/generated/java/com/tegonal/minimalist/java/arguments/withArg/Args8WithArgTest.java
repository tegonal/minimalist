// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generateTestJava
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.minimalist.java.arguments.withArg;

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

public class Args8WithArgTest {

	@Test
	public void withArg1() {
		var args = Args.of(
			1,
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
			"rep 7",
			"rep 8"
		);
		var argsResult = args.withArg1(2, "new rep");

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), 1);
			toEqual(getA2(e), 2L);
			toEqual(getA3(e), 3F);
			toEqual(getA4(e), 4.0);
			toEqual(getA5(e), 'c');
			toEqual(getA6(e), "string");
			toEqual(getA7(e), LocalDate.now());
			toEqual(getA8(e), (short) 1);
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), 2);
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), args.getA5());
			toEqual(getA6(e), args.getA6());
			toEqual(getA7(e), args.getA7());
			toEqual(getA8(e), args.getA8());
			toEqual(getRepresentation1(e), "new rep");
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), args.getRepresentation5());
			toEqual(getRepresentation6(e), args.getRepresentation6());
			toEqual(getRepresentation7(e), args.getRepresentation7());
			toEqual(getRepresentation8(e), args.getRepresentation8());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void withArg2() {
		var args = Args.of(
			1,
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
			"rep 7",
			"rep 8"
		);
		var argsResult = args.withArg2(3L, "new rep");

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), 1);
			toEqual(getA2(e), 2L);
			toEqual(getA3(e), 3F);
			toEqual(getA4(e), 4.0);
			toEqual(getA5(e), 'c');
			toEqual(getA6(e), "string");
			toEqual(getA7(e), LocalDate.now());
			toEqual(getA8(e), (short) 1);
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), 3L);
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), args.getA5());
			toEqual(getA6(e), args.getA6());
			toEqual(getA7(e), args.getA7());
			toEqual(getA8(e), args.getA8());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), "new rep");
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), args.getRepresentation5());
			toEqual(getRepresentation6(e), args.getRepresentation6());
			toEqual(getRepresentation7(e), args.getRepresentation7());
			toEqual(getRepresentation8(e), args.getRepresentation8());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void withArg3() {
		var args = Args.of(
			1,
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
			"rep 7",
			"rep 8"
		);
		var argsResult = args.withArg3(4F, "new rep");

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), 1);
			toEqual(getA2(e), 2L);
			toEqual(getA3(e), 3F);
			toEqual(getA4(e), 4.0);
			toEqual(getA5(e), 'c');
			toEqual(getA6(e), "string");
			toEqual(getA7(e), LocalDate.now());
			toEqual(getA8(e), (short) 1);
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), 4F);
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), args.getA5());
			toEqual(getA6(e), args.getA6());
			toEqual(getA7(e), args.getA7());
			toEqual(getA8(e), args.getA8());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), "new rep");
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), args.getRepresentation5());
			toEqual(getRepresentation6(e), args.getRepresentation6());
			toEqual(getRepresentation7(e), args.getRepresentation7());
			toEqual(getRepresentation8(e), args.getRepresentation8());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void withArg4() {
		var args = Args.of(
			1,
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
			"rep 7",
			"rep 8"
		);
		var argsResult = args.withArg4(5.0, "new rep");

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), 1);
			toEqual(getA2(e), 2L);
			toEqual(getA3(e), 3F);
			toEqual(getA4(e), 4.0);
			toEqual(getA5(e), 'c');
			toEqual(getA6(e), "string");
			toEqual(getA7(e), LocalDate.now());
			toEqual(getA8(e), (short) 1);
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), 5.0);
			toEqual(getA5(e), args.getA5());
			toEqual(getA6(e), args.getA6());
			toEqual(getA7(e), args.getA7());
			toEqual(getA8(e), args.getA8());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), "new rep");
			toEqual(getRepresentation5(e), args.getRepresentation5());
			toEqual(getRepresentation6(e), args.getRepresentation6());
			toEqual(getRepresentation7(e), args.getRepresentation7());
			toEqual(getRepresentation8(e), args.getRepresentation8());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void withArg5() {
		var args = Args.of(
			1,
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
			"rep 7",
			"rep 8"
		);
		var argsResult = args.withArg5('d', "new rep");

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), 1);
			toEqual(getA2(e), 2L);
			toEqual(getA3(e), 3F);
			toEqual(getA4(e), 4.0);
			toEqual(getA5(e), 'c');
			toEqual(getA6(e), "string");
			toEqual(getA7(e), LocalDate.now());
			toEqual(getA8(e), (short) 1);
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), 'd');
			toEqual(getA6(e), args.getA6());
			toEqual(getA7(e), args.getA7());
			toEqual(getA8(e), args.getA8());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), "new rep");
			toEqual(getRepresentation6(e), args.getRepresentation6());
			toEqual(getRepresentation7(e), args.getRepresentation7());
			toEqual(getRepresentation8(e), args.getRepresentation8());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void withArg6() {
		var args = Args.of(
			1,
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
			"rep 7",
			"rep 8"
		);
		var argsResult = args.withArg6("another string", "new rep");

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), 1);
			toEqual(getA2(e), 2L);
			toEqual(getA3(e), 3F);
			toEqual(getA4(e), 4.0);
			toEqual(getA5(e), 'c');
			toEqual(getA6(e), "string");
			toEqual(getA7(e), LocalDate.now());
			toEqual(getA8(e), (short) 1);
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), args.getA5());
			toEqual(getA6(e), "another string");
			toEqual(getA7(e), args.getA7());
			toEqual(getA8(e), args.getA8());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), args.getRepresentation5());
			toEqual(getRepresentation6(e), "new rep");
			toEqual(getRepresentation7(e), args.getRepresentation7());
			toEqual(getRepresentation8(e), args.getRepresentation8());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void withArg7() {
		var args = Args.of(
			1,
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
			"rep 7",
			"rep 8"
		);
		var argsResult = args.withArg7(LocalDate.now().plusDays(2), "new rep");

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), 1);
			toEqual(getA2(e), 2L);
			toEqual(getA3(e), 3F);
			toEqual(getA4(e), 4.0);
			toEqual(getA5(e), 'c');
			toEqual(getA6(e), "string");
			toEqual(getA7(e), LocalDate.now());
			toEqual(getA8(e), (short) 1);
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), args.getA5());
			toEqual(getA6(e), args.getA6());
			toEqual(getA7(e), LocalDate.now().plusDays(2));
			toEqual(getA8(e), args.getA8());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), args.getRepresentation5());
			toEqual(getRepresentation6(e), args.getRepresentation6());
			toEqual(getRepresentation7(e), "new rep");
			toEqual(getRepresentation8(e), args.getRepresentation8());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void withArg8() {
		var args = Args.of(
			1,
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
			"rep 7",
			"rep 8"
		);
		var argsResult = args.withArg8((short) 2, "new rep");

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), 1);
			toEqual(getA2(e), 2L);
			toEqual(getA3(e), 3F);
			toEqual(getA4(e), 4.0);
			toEqual(getA5(e), 'c');
			toEqual(getA6(e), "string");
			toEqual(getA7(e), LocalDate.now());
			toEqual(getA8(e), (short) 1);
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), args.getA5());
			toEqual(getA6(e), args.getA6());
			toEqual(getA7(e), args.getA7());
			toEqual(getA8(e), (short) 2);
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), args.getRepresentation5());
			toEqual(getRepresentation6(e), args.getRepresentation6());
			toEqual(getRepresentation7(e), args.getRepresentation7());
			toEqual(getRepresentation8(e), "new rep");
			return Unit.INSTANCE;
		});
	}

}
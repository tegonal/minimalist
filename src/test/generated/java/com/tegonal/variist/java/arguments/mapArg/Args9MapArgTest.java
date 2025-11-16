// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generateTestJava
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.variist.java.arguments.mapArg;

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

public class Args9MapArgTest {

	@Test
	public void mapArg1() {
		var args = Args.of(
			List.of(1),
			List.of(2L),
			List.of(3F),
			List.of(4.0),
			List.of('c'),
			List.of("string"),
			List.of(LocalDate.now()),
			List.of((short) 1),
			List.of((byte) 2),
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
		var argsResult = args.mapArg1(it -> it.get(0));

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), List.of(1));
			toEqual(getA2(e), List.of(2L));
			toEqual(getA3(e), List.of(3F));
			toEqual(getA4(e), List.of(4.0));
			toEqual(getA5(e), List.of('c'));
			toEqual(getA6(e), List.of("string"));
			toEqual(getA7(e), List.of(LocalDate.now()));
			toEqual(getA8(e), List.of((short) 1));
			toEqual(getA9(e), List.of((byte) 2));
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			toEqual(getRepresentation9(e), "rep 9");
				return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), 1);
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), args.getA5());
			toEqual(getA6(e), args.getA6());
			toEqual(getA7(e), args.getA7());
			toEqual(getA8(e), args.getA8());
			toEqual(getA9(e), args.getA9());
			toEqual(getRepresentation1(e), null);
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

	@Test
	public void mapArg1WithRepresentation() {
		var args = Args.of(
			List.of(1),
			List.of(2L),
			List.of(3F),
			List.of(4.0),
			List.of('c'),
			List.of("string"),
			List.of(LocalDate.now()),
			List.of((short) 1),
			List.of((byte) 2),
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
		var argsResult = args.mapArg1WithRepresentation((arg, repr) -> new Pair<>(arg.get(0), repr + " modified"));

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), List.of(1));
			toEqual(getA2(e), List.of(2L));
			toEqual(getA3(e), List.of(3F));
			toEqual(getA4(e), List.of(4.0));
			toEqual(getA5(e), List.of('c'));
			toEqual(getA6(e), List.of("string"));
			toEqual(getA7(e), List.of(LocalDate.now()));
			toEqual(getA8(e), List.of((short) 1));
			toEqual(getA9(e), List.of((byte) 2));
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			toEqual(getRepresentation9(e), "rep 9");
				return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), 1);
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), args.getA5());
			toEqual(getA6(e), args.getA6());
			toEqual(getA7(e), args.getA7());
			toEqual(getA8(e), args.getA8());
			toEqual(getA9(e), args.getA9());
			toEqual(getRepresentation1(e), "rep 1 modified");
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

	@Test
	public void mapArg2() {
		var args = Args.of(
			List.of(1),
			List.of(2L),
			List.of(3F),
			List.of(4.0),
			List.of('c'),
			List.of("string"),
			List.of(LocalDate.now()),
			List.of((short) 1),
			List.of((byte) 2),
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
		var argsResult = args.mapArg2(it -> it.get(0));

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), List.of(1));
			toEqual(getA2(e), List.of(2L));
			toEqual(getA3(e), List.of(3F));
			toEqual(getA4(e), List.of(4.0));
			toEqual(getA5(e), List.of('c'));
			toEqual(getA6(e), List.of("string"));
			toEqual(getA7(e), List.of(LocalDate.now()));
			toEqual(getA8(e), List.of((short) 1));
			toEqual(getA9(e), List.of((byte) 2));
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			toEqual(getRepresentation9(e), "rep 9");
				return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), 2L);
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), args.getA5());
			toEqual(getA6(e), args.getA6());
			toEqual(getA7(e), args.getA7());
			toEqual(getA8(e), args.getA8());
			toEqual(getA9(e), args.getA9());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), null);
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

	@Test
	public void mapArg2WithRepresentation() {
		var args = Args.of(
			List.of(1),
			List.of(2L),
			List.of(3F),
			List.of(4.0),
			List.of('c'),
			List.of("string"),
			List.of(LocalDate.now()),
			List.of((short) 1),
			List.of((byte) 2),
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
		var argsResult = args.mapArg2WithRepresentation((arg, repr) -> new Pair<>(arg.get(0), repr + " modified"));

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), List.of(1));
			toEqual(getA2(e), List.of(2L));
			toEqual(getA3(e), List.of(3F));
			toEqual(getA4(e), List.of(4.0));
			toEqual(getA5(e), List.of('c'));
			toEqual(getA6(e), List.of("string"));
			toEqual(getA7(e), List.of(LocalDate.now()));
			toEqual(getA8(e), List.of((short) 1));
			toEqual(getA9(e), List.of((byte) 2));
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			toEqual(getRepresentation9(e), "rep 9");
				return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), 2L);
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), args.getA5());
			toEqual(getA6(e), args.getA6());
			toEqual(getA7(e), args.getA7());
			toEqual(getA8(e), args.getA8());
			toEqual(getA9(e), args.getA9());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), "rep 2 modified");
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

	@Test
	public void mapArg3() {
		var args = Args.of(
			List.of(1),
			List.of(2L),
			List.of(3F),
			List.of(4.0),
			List.of('c'),
			List.of("string"),
			List.of(LocalDate.now()),
			List.of((short) 1),
			List.of((byte) 2),
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
		var argsResult = args.mapArg3(it -> it.get(0));

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), List.of(1));
			toEqual(getA2(e), List.of(2L));
			toEqual(getA3(e), List.of(3F));
			toEqual(getA4(e), List.of(4.0));
			toEqual(getA5(e), List.of('c'));
			toEqual(getA6(e), List.of("string"));
			toEqual(getA7(e), List.of(LocalDate.now()));
			toEqual(getA8(e), List.of((short) 1));
			toEqual(getA9(e), List.of((byte) 2));
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			toEqual(getRepresentation9(e), "rep 9");
				return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), 3F);
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), args.getA5());
			toEqual(getA6(e), args.getA6());
			toEqual(getA7(e), args.getA7());
			toEqual(getA8(e), args.getA8());
			toEqual(getA9(e), args.getA9());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), null);
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), args.getRepresentation5());
			toEqual(getRepresentation6(e), args.getRepresentation6());
			toEqual(getRepresentation7(e), args.getRepresentation7());
			toEqual(getRepresentation8(e), args.getRepresentation8());
			toEqual(getRepresentation9(e), args.getRepresentation9());
				return Unit.INSTANCE;
		});
	}

	@Test
	public void mapArg3WithRepresentation() {
		var args = Args.of(
			List.of(1),
			List.of(2L),
			List.of(3F),
			List.of(4.0),
			List.of('c'),
			List.of("string"),
			List.of(LocalDate.now()),
			List.of((short) 1),
			List.of((byte) 2),
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
		var argsResult = args.mapArg3WithRepresentation((arg, repr) -> new Pair<>(arg.get(0), repr + " modified"));

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), List.of(1));
			toEqual(getA2(e), List.of(2L));
			toEqual(getA3(e), List.of(3F));
			toEqual(getA4(e), List.of(4.0));
			toEqual(getA5(e), List.of('c'));
			toEqual(getA6(e), List.of("string"));
			toEqual(getA7(e), List.of(LocalDate.now()));
			toEqual(getA8(e), List.of((short) 1));
			toEqual(getA9(e), List.of((byte) 2));
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			toEqual(getRepresentation9(e), "rep 9");
				return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), 3F);
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), args.getA5());
			toEqual(getA6(e), args.getA6());
			toEqual(getA7(e), args.getA7());
			toEqual(getA8(e), args.getA8());
			toEqual(getA9(e), args.getA9());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), "rep 3 modified");
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), args.getRepresentation5());
			toEqual(getRepresentation6(e), args.getRepresentation6());
			toEqual(getRepresentation7(e), args.getRepresentation7());
			toEqual(getRepresentation8(e), args.getRepresentation8());
			toEqual(getRepresentation9(e), args.getRepresentation9());
				return Unit.INSTANCE;
		});
	}

	@Test
	public void mapArg4() {
		var args = Args.of(
			List.of(1),
			List.of(2L),
			List.of(3F),
			List.of(4.0),
			List.of('c'),
			List.of("string"),
			List.of(LocalDate.now()),
			List.of((short) 1),
			List.of((byte) 2),
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
		var argsResult = args.mapArg4(it -> it.get(0));

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), List.of(1));
			toEqual(getA2(e), List.of(2L));
			toEqual(getA3(e), List.of(3F));
			toEqual(getA4(e), List.of(4.0));
			toEqual(getA5(e), List.of('c'));
			toEqual(getA6(e), List.of("string"));
			toEqual(getA7(e), List.of(LocalDate.now()));
			toEqual(getA8(e), List.of((short) 1));
			toEqual(getA9(e), List.of((byte) 2));
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			toEqual(getRepresentation9(e), "rep 9");
				return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), 4.0);
			toEqual(getA5(e), args.getA5());
			toEqual(getA6(e), args.getA6());
			toEqual(getA7(e), args.getA7());
			toEqual(getA8(e), args.getA8());
			toEqual(getA9(e), args.getA9());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), null);
			toEqual(getRepresentation5(e), args.getRepresentation5());
			toEqual(getRepresentation6(e), args.getRepresentation6());
			toEqual(getRepresentation7(e), args.getRepresentation7());
			toEqual(getRepresentation8(e), args.getRepresentation8());
			toEqual(getRepresentation9(e), args.getRepresentation9());
				return Unit.INSTANCE;
		});
	}

	@Test
	public void mapArg4WithRepresentation() {
		var args = Args.of(
			List.of(1),
			List.of(2L),
			List.of(3F),
			List.of(4.0),
			List.of('c'),
			List.of("string"),
			List.of(LocalDate.now()),
			List.of((short) 1),
			List.of((byte) 2),
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
		var argsResult = args.mapArg4WithRepresentation((arg, repr) -> new Pair<>(arg.get(0), repr + " modified"));

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), List.of(1));
			toEqual(getA2(e), List.of(2L));
			toEqual(getA3(e), List.of(3F));
			toEqual(getA4(e), List.of(4.0));
			toEqual(getA5(e), List.of('c'));
			toEqual(getA6(e), List.of("string"));
			toEqual(getA7(e), List.of(LocalDate.now()));
			toEqual(getA8(e), List.of((short) 1));
			toEqual(getA9(e), List.of((byte) 2));
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			toEqual(getRepresentation9(e), "rep 9");
				return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), 4.0);
			toEqual(getA5(e), args.getA5());
			toEqual(getA6(e), args.getA6());
			toEqual(getA7(e), args.getA7());
			toEqual(getA8(e), args.getA8());
			toEqual(getA9(e), args.getA9());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), "rep 4 modified");
			toEqual(getRepresentation5(e), args.getRepresentation5());
			toEqual(getRepresentation6(e), args.getRepresentation6());
			toEqual(getRepresentation7(e), args.getRepresentation7());
			toEqual(getRepresentation8(e), args.getRepresentation8());
			toEqual(getRepresentation9(e), args.getRepresentation9());
				return Unit.INSTANCE;
		});
	}

	@Test
	public void mapArg5() {
		var args = Args.of(
			List.of(1),
			List.of(2L),
			List.of(3F),
			List.of(4.0),
			List.of('c'),
			List.of("string"),
			List.of(LocalDate.now()),
			List.of((short) 1),
			List.of((byte) 2),
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
		var argsResult = args.mapArg5(it -> it.get(0));

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), List.of(1));
			toEqual(getA2(e), List.of(2L));
			toEqual(getA3(e), List.of(3F));
			toEqual(getA4(e), List.of(4.0));
			toEqual(getA5(e), List.of('c'));
			toEqual(getA6(e), List.of("string"));
			toEqual(getA7(e), List.of(LocalDate.now()));
			toEqual(getA8(e), List.of((short) 1));
			toEqual(getA9(e), List.of((byte) 2));
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			toEqual(getRepresentation9(e), "rep 9");
				return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), 'c');
			toEqual(getA6(e), args.getA6());
			toEqual(getA7(e), args.getA7());
			toEqual(getA8(e), args.getA8());
			toEqual(getA9(e), args.getA9());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), null);
			toEqual(getRepresentation6(e), args.getRepresentation6());
			toEqual(getRepresentation7(e), args.getRepresentation7());
			toEqual(getRepresentation8(e), args.getRepresentation8());
			toEqual(getRepresentation9(e), args.getRepresentation9());
				return Unit.INSTANCE;
		});
	}

	@Test
	public void mapArg5WithRepresentation() {
		var args = Args.of(
			List.of(1),
			List.of(2L),
			List.of(3F),
			List.of(4.0),
			List.of('c'),
			List.of("string"),
			List.of(LocalDate.now()),
			List.of((short) 1),
			List.of((byte) 2),
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
		var argsResult = args.mapArg5WithRepresentation((arg, repr) -> new Pair<>(arg.get(0), repr + " modified"));

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), List.of(1));
			toEqual(getA2(e), List.of(2L));
			toEqual(getA3(e), List.of(3F));
			toEqual(getA4(e), List.of(4.0));
			toEqual(getA5(e), List.of('c'));
			toEqual(getA6(e), List.of("string"));
			toEqual(getA7(e), List.of(LocalDate.now()));
			toEqual(getA8(e), List.of((short) 1));
			toEqual(getA9(e), List.of((byte) 2));
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			toEqual(getRepresentation9(e), "rep 9");
				return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), 'c');
			toEqual(getA6(e), args.getA6());
			toEqual(getA7(e), args.getA7());
			toEqual(getA8(e), args.getA8());
			toEqual(getA9(e), args.getA9());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), "rep 5 modified");
			toEqual(getRepresentation6(e), args.getRepresentation6());
			toEqual(getRepresentation7(e), args.getRepresentation7());
			toEqual(getRepresentation8(e), args.getRepresentation8());
			toEqual(getRepresentation9(e), args.getRepresentation9());
				return Unit.INSTANCE;
		});
	}

	@Test
	public void mapArg6() {
		var args = Args.of(
			List.of(1),
			List.of(2L),
			List.of(3F),
			List.of(4.0),
			List.of('c'),
			List.of("string"),
			List.of(LocalDate.now()),
			List.of((short) 1),
			List.of((byte) 2),
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
		var argsResult = args.mapArg6(it -> it.get(0));

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), List.of(1));
			toEqual(getA2(e), List.of(2L));
			toEqual(getA3(e), List.of(3F));
			toEqual(getA4(e), List.of(4.0));
			toEqual(getA5(e), List.of('c'));
			toEqual(getA6(e), List.of("string"));
			toEqual(getA7(e), List.of(LocalDate.now()));
			toEqual(getA8(e), List.of((short) 1));
			toEqual(getA9(e), List.of((byte) 2));
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			toEqual(getRepresentation9(e), "rep 9");
				return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), args.getA5());
			toEqual(getA6(e), "string");
			toEqual(getA7(e), args.getA7());
			toEqual(getA8(e), args.getA8());
			toEqual(getA9(e), args.getA9());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), args.getRepresentation5());
			toEqual(getRepresentation6(e), null);
			toEqual(getRepresentation7(e), args.getRepresentation7());
			toEqual(getRepresentation8(e), args.getRepresentation8());
			toEqual(getRepresentation9(e), args.getRepresentation9());
				return Unit.INSTANCE;
		});
	}

	@Test
	public void mapArg6WithRepresentation() {
		var args = Args.of(
			List.of(1),
			List.of(2L),
			List.of(3F),
			List.of(4.0),
			List.of('c'),
			List.of("string"),
			List.of(LocalDate.now()),
			List.of((short) 1),
			List.of((byte) 2),
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
		var argsResult = args.mapArg6WithRepresentation((arg, repr) -> new Pair<>(arg.get(0), repr + " modified"));

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), List.of(1));
			toEqual(getA2(e), List.of(2L));
			toEqual(getA3(e), List.of(3F));
			toEqual(getA4(e), List.of(4.0));
			toEqual(getA5(e), List.of('c'));
			toEqual(getA6(e), List.of("string"));
			toEqual(getA7(e), List.of(LocalDate.now()));
			toEqual(getA8(e), List.of((short) 1));
			toEqual(getA9(e), List.of((byte) 2));
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			toEqual(getRepresentation9(e), "rep 9");
				return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), args.getA5());
			toEqual(getA6(e), "string");
			toEqual(getA7(e), args.getA7());
			toEqual(getA8(e), args.getA8());
			toEqual(getA9(e), args.getA9());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), args.getRepresentation5());
			toEqual(getRepresentation6(e), "rep 6 modified");
			toEqual(getRepresentation7(e), args.getRepresentation7());
			toEqual(getRepresentation8(e), args.getRepresentation8());
			toEqual(getRepresentation9(e), args.getRepresentation9());
				return Unit.INSTANCE;
		});
	}

	@Test
	public void mapArg7() {
		var args = Args.of(
			List.of(1),
			List.of(2L),
			List.of(3F),
			List.of(4.0),
			List.of('c'),
			List.of("string"),
			List.of(LocalDate.now()),
			List.of((short) 1),
			List.of((byte) 2),
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
		var argsResult = args.mapArg7(it -> it.get(0));

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), List.of(1));
			toEqual(getA2(e), List.of(2L));
			toEqual(getA3(e), List.of(3F));
			toEqual(getA4(e), List.of(4.0));
			toEqual(getA5(e), List.of('c'));
			toEqual(getA6(e), List.of("string"));
			toEqual(getA7(e), List.of(LocalDate.now()));
			toEqual(getA8(e), List.of((short) 1));
			toEqual(getA9(e), List.of((byte) 2));
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			toEqual(getRepresentation9(e), "rep 9");
				return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), args.getA5());
			toEqual(getA6(e), args.getA6());
			toEqual(getA7(e), LocalDate.now());
			toEqual(getA8(e), args.getA8());
			toEqual(getA9(e), args.getA9());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), args.getRepresentation5());
			toEqual(getRepresentation6(e), args.getRepresentation6());
			toEqual(getRepresentation7(e), null);
			toEqual(getRepresentation8(e), args.getRepresentation8());
			toEqual(getRepresentation9(e), args.getRepresentation9());
				return Unit.INSTANCE;
		});
	}

	@Test
	public void mapArg7WithRepresentation() {
		var args = Args.of(
			List.of(1),
			List.of(2L),
			List.of(3F),
			List.of(4.0),
			List.of('c'),
			List.of("string"),
			List.of(LocalDate.now()),
			List.of((short) 1),
			List.of((byte) 2),
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
		var argsResult = args.mapArg7WithRepresentation((arg, repr) -> new Pair<>(arg.get(0), repr + " modified"));

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), List.of(1));
			toEqual(getA2(e), List.of(2L));
			toEqual(getA3(e), List.of(3F));
			toEqual(getA4(e), List.of(4.0));
			toEqual(getA5(e), List.of('c'));
			toEqual(getA6(e), List.of("string"));
			toEqual(getA7(e), List.of(LocalDate.now()));
			toEqual(getA8(e), List.of((short) 1));
			toEqual(getA9(e), List.of((byte) 2));
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			toEqual(getRepresentation9(e), "rep 9");
				return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA3());
			toEqual(getA4(e), args.getA4());
			toEqual(getA5(e), args.getA5());
			toEqual(getA6(e), args.getA6());
			toEqual(getA7(e), LocalDate.now());
			toEqual(getA8(e), args.getA8());
			toEqual(getA9(e), args.getA9());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), args.getRepresentation5());
			toEqual(getRepresentation6(e), args.getRepresentation6());
			toEqual(getRepresentation7(e), "rep 7 modified");
			toEqual(getRepresentation8(e), args.getRepresentation8());
			toEqual(getRepresentation9(e), args.getRepresentation9());
				return Unit.INSTANCE;
		});
	}

	@Test
	public void mapArg8() {
		var args = Args.of(
			List.of(1),
			List.of(2L),
			List.of(3F),
			List.of(4.0),
			List.of('c'),
			List.of("string"),
			List.of(LocalDate.now()),
			List.of((short) 1),
			List.of((byte) 2),
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
		var argsResult = args.mapArg8(it -> it.get(0));

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), List.of(1));
			toEqual(getA2(e), List.of(2L));
			toEqual(getA3(e), List.of(3F));
			toEqual(getA4(e), List.of(4.0));
			toEqual(getA5(e), List.of('c'));
			toEqual(getA6(e), List.of("string"));
			toEqual(getA7(e), List.of(LocalDate.now()));
			toEqual(getA8(e), List.of((short) 1));
			toEqual(getA9(e), List.of((byte) 2));
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			toEqual(getRepresentation9(e), "rep 9");
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
			toEqual(getA8(e), (short) 1);
			toEqual(getA9(e), args.getA9());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), args.getRepresentation5());
			toEqual(getRepresentation6(e), args.getRepresentation6());
			toEqual(getRepresentation7(e), args.getRepresentation7());
			toEqual(getRepresentation8(e), null);
			toEqual(getRepresentation9(e), args.getRepresentation9());
				return Unit.INSTANCE;
		});
	}

	@Test
	public void mapArg8WithRepresentation() {
		var args = Args.of(
			List.of(1),
			List.of(2L),
			List.of(3F),
			List.of(4.0),
			List.of('c'),
			List.of("string"),
			List.of(LocalDate.now()),
			List.of((short) 1),
			List.of((byte) 2),
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
		var argsResult = args.mapArg8WithRepresentation((arg, repr) -> new Pair<>(arg.get(0), repr + " modified"));

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), List.of(1));
			toEqual(getA2(e), List.of(2L));
			toEqual(getA3(e), List.of(3F));
			toEqual(getA4(e), List.of(4.0));
			toEqual(getA5(e), List.of('c'));
			toEqual(getA6(e), List.of("string"));
			toEqual(getA7(e), List.of(LocalDate.now()));
			toEqual(getA8(e), List.of((short) 1));
			toEqual(getA9(e), List.of((byte) 2));
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			toEqual(getRepresentation9(e), "rep 9");
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
			toEqual(getA8(e), (short) 1);
			toEqual(getA9(e), args.getA9());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), args.getRepresentation5());
			toEqual(getRepresentation6(e), args.getRepresentation6());
			toEqual(getRepresentation7(e), args.getRepresentation7());
			toEqual(getRepresentation8(e), "rep 8 modified");
			toEqual(getRepresentation9(e), args.getRepresentation9());
				return Unit.INSTANCE;
		});
	}

	@Test
	public void mapArg9() {
		var args = Args.of(
			List.of(1),
			List.of(2L),
			List.of(3F),
			List.of(4.0),
			List.of('c'),
			List.of("string"),
			List.of(LocalDate.now()),
			List.of((short) 1),
			List.of((byte) 2),
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
		var argsResult = args.mapArg9(it -> it.get(0));

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), List.of(1));
			toEqual(getA2(e), List.of(2L));
			toEqual(getA3(e), List.of(3F));
			toEqual(getA4(e), List.of(4.0));
			toEqual(getA5(e), List.of('c'));
			toEqual(getA6(e), List.of("string"));
			toEqual(getA7(e), List.of(LocalDate.now()));
			toEqual(getA8(e), List.of((short) 1));
			toEqual(getA9(e), List.of((byte) 2));
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			toEqual(getRepresentation9(e), "rep 9");
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
			toEqual(getA8(e), args.getA8());
			toEqual(getA9(e), (byte) 2);
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), args.getRepresentation5());
			toEqual(getRepresentation6(e), args.getRepresentation6());
			toEqual(getRepresentation7(e), args.getRepresentation7());
			toEqual(getRepresentation8(e), args.getRepresentation8());
			toEqual(getRepresentation9(e), null);
				return Unit.INSTANCE;
		});
	}

	@Test
	public void mapArg9WithRepresentation() {
		var args = Args.of(
			List.of(1),
			List.of(2L),
			List.of(3F),
			List.of(4.0),
			List.of('c'),
			List.of("string"),
			List.of(LocalDate.now()),
			List.of((short) 1),
			List.of((byte) 2),
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
		var argsResult = args.mapArg9WithRepresentation((arg, repr) -> new Pair<>(arg.get(0), repr + " modified"));

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), List.of(1));
			toEqual(getA2(e), List.of(2L));
			toEqual(getA3(e), List.of(3F));
			toEqual(getA4(e), List.of(4.0));
			toEqual(getA5(e), List.of('c'));
			toEqual(getA6(e), List.of("string"));
			toEqual(getA7(e), List.of(LocalDate.now()));
			toEqual(getA8(e), List.of((short) 1));
			toEqual(getA9(e), List.of((byte) 2));
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			toEqual(getRepresentation4(e), "rep 4");
			toEqual(getRepresentation5(e), "rep 5");
			toEqual(getRepresentation6(e), "rep 6");
			toEqual(getRepresentation7(e), "rep 7");
			toEqual(getRepresentation8(e), "rep 8");
			toEqual(getRepresentation9(e), "rep 9");
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
			toEqual(getA8(e), args.getA8());
			toEqual(getA9(e), (byte) 2);
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			toEqual(getRepresentation4(e), args.getRepresentation4());
			toEqual(getRepresentation5(e), args.getRepresentation5());
			toEqual(getRepresentation6(e), args.getRepresentation6());
			toEqual(getRepresentation7(e), args.getRepresentation7());
			toEqual(getRepresentation8(e), args.getRepresentation8());
			toEqual(getRepresentation9(e), "rep 9 modified");
				return Unit.INSTANCE;
		});
	}

}

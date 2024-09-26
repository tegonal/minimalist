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

public class Args3WithArgTest {

	@Test
	public void withArg1() {
		var args = Args.of(
			1,
			2L,
			3F,
			new Representation("rep 1"),
			"rep 2",
			"rep 3"
		);
		var argsResult = args.withArg1(2, "new rep");

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), 1);
			toEqual(getA2(e), 2L);
			toEqual(getA3(e), 3F);
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), 2);
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), args.getA3());
			toEqual(getRepresentation1(e), "new rep");
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), args.getRepresentation3());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void withArg2() {
		var args = Args.of(
			1,
			2L,
			3F,
			new Representation("rep 1"),
			"rep 2",
			"rep 3"
		);
		var argsResult = args.withArg2(3L, "new rep");

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), 1);
			toEqual(getA2(e), 2L);
			toEqual(getA3(e), 3F);
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), 3L);
			toEqual(getA3(e), args.getA3());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), "new rep");
			toEqual(getRepresentation3(e), args.getRepresentation3());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void withArg3() {
		var args = Args.of(
			1,
			2L,
			3F,
			new Representation("rep 1"),
			"rep 2",
			"rep 3"
		);
		var argsResult = args.withArg3(4F, "new rep");

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), 1);
			toEqual(getA2(e), 2L);
			toEqual(getA3(e), 3F);
			toEqual(getRepresentation1(e), "rep 1");
			toEqual(getRepresentation2(e), "rep 2");
			toEqual(getRepresentation3(e), "rep 3");
			return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getA2(e), args.getA2());
			toEqual(getA3(e), 4F);
			toEqual(getRepresentation1(e), args.getRepresentation1());
			toEqual(getRepresentation2(e), args.getRepresentation2());
			toEqual(getRepresentation3(e), "new rep");
			return Unit.INSTANCE;
		});
	}

}
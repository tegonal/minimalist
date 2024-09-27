// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generateTestJava
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.minimalist.java.arguments.mapArg;

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

public class Args1MapArgTest {

	@Test
	public void mapArg1() {
		var args = Args.of(
			List.of(1),
			new Representation("rep 1")
		);
		var argsResult = args.mapArg1(it -> it.get(0));

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), List.of(1));
			toEqual(getRepresentation1(e), "rep 1");
				return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), 1);
			toEqual(getRepresentation1(e), null);
				return Unit.INSTANCE;
		});
	}

	@Test
	public void mapArg1WithRepresentation() {
		var args = Args.of(
			List.of(1),
			new Representation("rep 1")
		);
		var argsResult = args.mapArg1WithRepresentation((arg, repr) -> new Pair<>(arg.get(0), repr + " modified"));

		// no changes to args
		expect(args, e -> {
			toEqual(getA1(e), List.of(1));
			toEqual(getRepresentation1(e), "rep 1");
				return Unit.INSTANCE;
		});

		expect(argsResult, e -> {
			toEqual(getA1(e), 1);
			toEqual(getRepresentation1(e), "rep 1 modified");
				return Unit.INSTANCE;
		});
	}

}
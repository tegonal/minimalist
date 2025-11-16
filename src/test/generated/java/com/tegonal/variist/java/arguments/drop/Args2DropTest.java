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

public class Args2DropTest {

	@Test
	public void dropArg1() {
		var args = Args.of(
			1,
			2L,
			new Representation("rep 1"),
			"rep 2"
		);
		Args1<Long> argsResult = args.dropArg1();
		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA2());
			toEqual(getRepresentation1(e), args.getRepresentation2());
			return Unit.INSTANCE;
		});
	}

	@Test
	public void dropArg2() {
		var args = Args.of(
			1,
			2L,
			new Representation("rep 1"),
			"rep 2"
		);
		Args1<Integer> argsResult = args.dropArg2();
		expect(argsResult, e -> {
			toEqual(getA1(e), args.getA1());
			toEqual(getRepresentation1(e), args.getRepresentation1());
			return Unit.INSTANCE;
		});
	}

}
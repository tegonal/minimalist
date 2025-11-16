// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generateTestJava
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.variist.java.arguments.annotation;

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

public class Args8ArgumentsTest {

	@Test
	public void get_returns_correct_array_and_value_not_wrapped_in_Named_if_representation_not_specified() {
		var args = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			(short) 1
		);
		new Pipe<>(expect(args.get()))
			.pipe((it) -> asList(it))
			.pipe(it -> IterableExpectationsKt.toContainExactly(
				it,
				args.getA1(),
				new Object[] {
					args.getA2(),
					args.getA3(),
					args.getA4(),
					args.getA5(),
					args.getA6(),
					args.getA7(),
					args.getA8()
				},
				o -> Unit.INSTANCE
			));
	}

	@Test
	public void get_returns_correct_array_and_value_wrapped_in_Named_if_representation_specified() {
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
		new Pipe<>(expect(args.get()))
			.pipe((it) -> asList(it))
			.pipe(it -> IterableExpectationsKt.toContainExactly(it,
				e -> {
					Class<Named<Integer>> clazz = (Class) Named.class;
					AnyExpectationsKt.toBeAnInstanceOf(e, kotlin.jvm.JvmClassMappingKt.getKotlinClass(clazz)).transformAndAppend(eNamed -> {
						NamedExpectationsKt.name(eNamed, eString -> {
							AnyExpectationsKt.toEqual(eString, args.getRepresentation1());
							return Unit.INSTANCE;
						});
						NamedExpectationsKt.payload(eNamed, eVal -> {
							AnyExpectationsKt.toEqual(eVal, args.getA1());
							return Unit.INSTANCE;
						});
						return Unit.INSTANCE;
					});
					return Unit.INSTANCE;
				},
				new Function1[] {
				untypedE -> {
					Expect<Object> e = (Expect) untypedE;
					Class<Named<Long>> clazz = (Class) Named.class;
					AnyExpectationsKt.toBeAnInstanceOf(e, kotlin.jvm.JvmClassMappingKt.getKotlinClass(clazz)).transformAndAppend(eNamed -> {
						NamedExpectationsKt.name(eNamed, eString -> {
							AnyExpectationsKt.toEqual(eString, args.getRepresentation2());
							return Unit.INSTANCE;
						});
						NamedExpectationsKt.payload(eNamed, eVal -> {
							AnyExpectationsKt.toEqual(eVal, args.getA2());
							return Unit.INSTANCE;
						});
						return Unit.INSTANCE;
					});
					return Unit.INSTANCE;
				},
				untypedE -> {
					Expect<Object> e = (Expect) untypedE;
					Class<Named<Float>> clazz = (Class) Named.class;
					AnyExpectationsKt.toBeAnInstanceOf(e, kotlin.jvm.JvmClassMappingKt.getKotlinClass(clazz)).transformAndAppend(eNamed -> {
						NamedExpectationsKt.name(eNamed, eString -> {
							AnyExpectationsKt.toEqual(eString, args.getRepresentation3());
							return Unit.INSTANCE;
						});
						NamedExpectationsKt.payload(eNamed, eVal -> {
							AnyExpectationsKt.toEqual(eVal, args.getA3());
							return Unit.INSTANCE;
						});
						return Unit.INSTANCE;
					});
					return Unit.INSTANCE;
				},
				untypedE -> {
					Expect<Object> e = (Expect) untypedE;
					Class<Named<Double>> clazz = (Class) Named.class;
					AnyExpectationsKt.toBeAnInstanceOf(e, kotlin.jvm.JvmClassMappingKt.getKotlinClass(clazz)).transformAndAppend(eNamed -> {
						NamedExpectationsKt.name(eNamed, eString -> {
							AnyExpectationsKt.toEqual(eString, args.getRepresentation4());
							return Unit.INSTANCE;
						});
						NamedExpectationsKt.payload(eNamed, eVal -> {
							AnyExpectationsKt.toEqual(eVal, args.getA4());
							return Unit.INSTANCE;
						});
						return Unit.INSTANCE;
					});
					return Unit.INSTANCE;
				},
				untypedE -> {
					Expect<Object> e = (Expect) untypedE;
					Class<Named<Character>> clazz = (Class) Named.class;
					AnyExpectationsKt.toBeAnInstanceOf(e, kotlin.jvm.JvmClassMappingKt.getKotlinClass(clazz)).transformAndAppend(eNamed -> {
						NamedExpectationsKt.name(eNamed, eString -> {
							AnyExpectationsKt.toEqual(eString, args.getRepresentation5());
							return Unit.INSTANCE;
						});
						NamedExpectationsKt.payload(eNamed, eVal -> {
							AnyExpectationsKt.toEqual(eVal, args.getA5());
							return Unit.INSTANCE;
						});
						return Unit.INSTANCE;
					});
					return Unit.INSTANCE;
				},
				untypedE -> {
					Expect<Object> e = (Expect) untypedE;
					Class<Named<String>> clazz = (Class) Named.class;
					AnyExpectationsKt.toBeAnInstanceOf(e, kotlin.jvm.JvmClassMappingKt.getKotlinClass(clazz)).transformAndAppend(eNamed -> {
						NamedExpectationsKt.name(eNamed, eString -> {
							AnyExpectationsKt.toEqual(eString, args.getRepresentation6());
							return Unit.INSTANCE;
						});
						NamedExpectationsKt.payload(eNamed, eVal -> {
							AnyExpectationsKt.toEqual(eVal, args.getA6());
							return Unit.INSTANCE;
						});
						return Unit.INSTANCE;
					});
					return Unit.INSTANCE;
				},
				untypedE -> {
					Expect<Object> e = (Expect) untypedE;
					Class<Named<LocalDate>> clazz = (Class) Named.class;
					AnyExpectationsKt.toBeAnInstanceOf(e, kotlin.jvm.JvmClassMappingKt.getKotlinClass(clazz)).transformAndAppend(eNamed -> {
						NamedExpectationsKt.name(eNamed, eString -> {
							AnyExpectationsKt.toEqual(eString, args.getRepresentation7());
							return Unit.INSTANCE;
						});
						NamedExpectationsKt.payload(eNamed, eVal -> {
							AnyExpectationsKt.toEqual(eVal, args.getA7());
							return Unit.INSTANCE;
						});
						return Unit.INSTANCE;
					});
					return Unit.INSTANCE;
				},
				untypedE -> {
					Expect<Object> e = (Expect) untypedE;
					Class<Named<Short>> clazz = (Class) Named.class;
					AnyExpectationsKt.toBeAnInstanceOf(e, kotlin.jvm.JvmClassMappingKt.getKotlinClass(clazz)).transformAndAppend(eNamed -> {
						NamedExpectationsKt.name(eNamed, eString -> {
							AnyExpectationsKt.toEqual(eString, args.getRepresentation8());
							return Unit.INSTANCE;
						});
						NamedExpectationsKt.payload(eNamed, eVal -> {
							AnyExpectationsKt.toEqual(eVal, args.getA8());
							return Unit.INSTANCE;
						});
						return Unit.INSTANCE;
					});
					return Unit.INSTANCE;
				}
				},
 				o -> Unit.INSTANCE
			));
	}

	@Test
	public void using_null_as_representation_does_not_wrap_it_into_Named() {
		var args = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			(short) 1,
			(Representation) null,
			(String) null,
			(String) null,
			(String) null,
			(String) null,
			(String) null,
			(String) null,
			(String) null
		);
		new Pipe<>(expect(args.get()))
			.pipe(it -> asList(it))
			.pipe(it -> IterableExpectationsKt.toContainExactly(
				it,
				args.getA1(),
				new Object[] {
					args.getA2(),
					args.getA3(),
					args.getA4(),
					args.getA5(),
					args.getA6(),
					args.getA7(),
					args.getA8()
				},
				o -> Unit.INSTANCE
			));
	}

	@ParameterizedTest
	@MethodSource("args")
	public void can_use_Args8_in_MethodSource(
		Integer a1,
		Long a2,
		Float a3,
		Double a4,
		Character a5,
		String a6,
		LocalDate a7,
		Short a8
	) {
		new Pipe<>(expect(a1)).pipe(it -> toEqual(it, 1));
		new Pipe<>(expect(a2)).pipe(it -> toEqual(it, 2L));
		new Pipe<>(expect(a3)).pipe(it -> toEqual(it, 3F));
		new Pipe<>(expect(a4)).pipe(it -> toEqual(it, 4.0));
		new Pipe<>(expect(a5)).pipe(it -> toEqual(it, 'c'));
		new Pipe<>(expect(a6)).pipe(it -> toEqual(it, "string"));
		new Pipe<>(expect(a7)).pipe(it -> toEqual(it, LocalDate.now()));
		new Pipe<>(expect(a8)).pipe(it -> toEqual(it, (short) 1));
	}

	static List<Args> args() {
		return List.of(Args.of(1, 2L, 3F, 4.0, 'c', "string", LocalDate.now(), (short) 1));
	}
}
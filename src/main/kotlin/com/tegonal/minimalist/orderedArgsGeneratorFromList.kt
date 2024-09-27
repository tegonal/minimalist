package com.tegonal.minimalist

import com.tegonal.minimalist.impl.ListArgsGenerator

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
@JvmName("fromValueList")
fun <T> OrderedArgsGenerator.Companion.fromList(args: List<T>): OrderedArgsGenerator<Args1<T>> =
    OrderedArgsGenerator.fromList(args.map { Args.of(it) })

/**
 * Returns an [OrderedArgsGenerator] based on the given [args].
 *
 * @return an [OrderedArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun <T : Args> OrderedArgsGenerator.Companion.fromList(args: List<T>): OrderedArgsGenerator<T> = ListArgsGenerator(args)


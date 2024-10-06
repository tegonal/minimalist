package com.tegonal.minimalist

import com.tegonal.minimalist.impl.RandomArgsGeneratorTransformer


fun <A1, A2> RandomArgsGenerator<Args1<A1>>.appendDependent(
	map: (Args1<A1>) -> RandomArgsGenerator<Args1<A2>>
): RandomArgsGenerator<Args2<A1, A2>> = this.map { it.append(map(it).generateOne()) }

fun <T : Args, R : Args> RandomArgsGenerator<T>.map(f: (T) -> R): RandomArgsGenerator<R> =
	this.transform { it.map(f) }

fun <T : Args> RandomArgsGenerator<T>.filter(f: (T) -> Boolean): RandomArgsGenerator<T> =
	this.transform { it.filter(f) }

fun <T : Args, R : Args> RandomArgsGenerator<T>.transform(f: (Sequence<T>) -> Sequence<R>): RandomArgsGenerator<R> =
	RandomArgsGeneratorTransformer(this, f)

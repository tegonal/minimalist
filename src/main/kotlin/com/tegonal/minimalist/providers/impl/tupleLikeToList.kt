package com.tegonal.minimalist.providers.impl

import ch.tutteli.kbox.Tuple2Like
import ch.tutteli.kbox.Tuple3Like
import ch.tutteli.kbox.Tuple4
import ch.tutteli.kbox.Tuple4Like
import ch.tutteli.kbox.Tuple5
import ch.tutteli.kbox.Tuple5Like
import ch.tutteli.kbox.Tuple6
import ch.tutteli.kbox.Tuple6Like
import ch.tutteli.kbox.Tuple7
import ch.tutteli.kbox.Tuple7Like
import ch.tutteli.kbox.Tuple8
import ch.tutteli.kbox.Tuple8Like
import ch.tutteli.kbox.Tuple9
import ch.tutteli.kbox.Tuple9Like
import ch.tutteli.kbox.toList

fun tupleLikeToList(maybeTupleLike: Any?): List<*>? = when (maybeTupleLike) {
	is Pair<*, *> -> maybeTupleLike.toList()
	is Triple<*, *, *> -> maybeTupleLike.toList()
	is Tuple4<*, *, *, *> -> maybeTupleLike.toList()
	is Tuple5<*, *, *, *, *> -> maybeTupleLike.toList()
	is Tuple6<*, *, *, *, *, *> -> maybeTupleLike.toList()
	is Tuple7<*, *, *, *, *, *, *> -> maybeTupleLike.toList()
	is Tuple8<*, *, *, *, *, *, *, *> -> maybeTupleLike.toList()
	is Tuple9<*, *, *, *, *, *, *, *, *> -> maybeTupleLike.toList()

	is Tuple2Like<*, *> -> listOf(maybeTupleLike.component1(), maybeTupleLike.component2())
	is Tuple3Like<*, *, *> -> listOf(
		maybeTupleLike.component1(),
		maybeTupleLike.component2(),
		maybeTupleLike.component3()
	)

	is Tuple4Like<*, *, *, *> -> listOf(
		maybeTupleLike.component1(),
		maybeTupleLike.component2(),
		maybeTupleLike.component3(),
		maybeTupleLike.component4()
	)

	is Tuple5Like<*, *, *, *, *> -> listOf(
		maybeTupleLike.component1(),
		maybeTupleLike.component2(),
		maybeTupleLike.component3(),
		maybeTupleLike.component4(),
		maybeTupleLike.component5()
	)

	is Tuple6Like<*, *, *, *, *, *> -> listOf(
		maybeTupleLike.component1(),
		maybeTupleLike.component2(),
		maybeTupleLike.component3(),
		maybeTupleLike.component4(),
		maybeTupleLike.component5(),
		maybeTupleLike.component6()
	)

	is Tuple7Like<*, *, *, *, *, *, *> -> listOf(
		maybeTupleLike.component1(),
		maybeTupleLike.component2(),
		maybeTupleLike.component3(),
		maybeTupleLike.component4(),
		maybeTupleLike.component5(),
		maybeTupleLike.component6(),
		maybeTupleLike.component7()
	)

	is Tuple8Like<*, *, *, *, *, *, *, *> -> listOf(
		maybeTupleLike.component1(),
		maybeTupleLike.component2(),
		maybeTupleLike.component3(),
		maybeTupleLike.component4(),
		maybeTupleLike.component5(),
		maybeTupleLike.component6(),
		maybeTupleLike.component7(),
		maybeTupleLike.component8()
	)

	is Tuple9Like<*, *, *, *, *, *, *, *, *> -> listOf(
		maybeTupleLike.component1(),
		maybeTupleLike.component2(),
		maybeTupleLike.component3(),
		maybeTupleLike.component4(),
		maybeTupleLike.component5(),
		maybeTupleLike.component6(),
		maybeTupleLike.component7(),
		maybeTupleLike.component8(),
		maybeTupleLike.component9()
	)

	else -> null
}

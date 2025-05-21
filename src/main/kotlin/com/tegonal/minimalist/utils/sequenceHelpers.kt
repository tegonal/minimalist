package com.tegonal.minimalist.utils

//TODO 2.0.0 check name
fun repeatForever(start: Int, endNotInclusive: Int): Sequence<Int> =
	generateSequence(start % endNotInclusive) { if (it + 1 < endNotInclusive) it + 1 else 0 }

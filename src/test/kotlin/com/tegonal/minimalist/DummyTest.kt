package com.tegonal.minimalist

import org.junit.jupiter.api.Test
import java.util.*
import kotlin.collections.set
import kotlin.random.Random
import kotlin.system.measureNanoTime


enum class A {
    A, B, C, D, E, F, G, H
}

class DummyTest {

	@Test
	fun test(){
		OrderedArgsGenerator.fromEnum<A>()
	}

    @Test
    fun `mainly here to see if setup works`() {
        listOf(1, 2, 3, 4).shuffled()
        val max = 60_000
        val maxNumber = 1_000_000
        bench(
            "int range" to {
                (0..maxNumber).shuffled().take(max).joinToString(", ")
            },
            "generateSequence " to {
                generateSequence(1) { it + 1 }.take(maxNumber).shuffled().take(max).joinToString(", ")
            },
            "sequence intrange" to {
                (0..maxNumber).asSequence().shuffled().take(max).joinToString(", ")
            },
            "bitset" to {
                val bs = BitSet(maxNumber)
                val numbers = Array(max) { 0 }
                var cardinality = 0
                while (cardinality < max) {
                    val v: Int = Random.nextInt(maxNumber + 1)
                    if (!bs[v]) {
                        bs.set(v)
                        numbers[cardinality] = v
                        cardinality++
                    }
                }
                numbers.joinToString(", ")
            },
            "hashset" to {
                val s = HashSet<Int>()
                while (s.size < max) s.add(Random.nextInt(maxNumber+1))
                s.joinToString(", ")
            },
            "random" to {
                Random.nextInt(maxNumber+1)
            }
        )
    }

    fun bench(vararg pairs: Pair<String, () -> Unit>) {
        val origin = pairs.clone()
        var map = hashMapOf(*pairs.map { it.first to 0L }.toTypedArray())
        repeat(100) {
            pairs.apply { shuffle() }.forEach {
                map[it.first] = map[it.first]!! + measureNanoTime { it.second() }
            }
        }
        origin.forEach {
            println("${it.first.padEnd(20)}: ${map[it.first]!!.toString().padStart(10)}")
        }
    }
}

package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.generators.ArgsGenerator
import com.tegonal.minimalist.generators.UnicodeRange
import com.tegonal.minimalist.utils.impl.FEATURE_REQUEST_URL

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
inline fun <A, B, R> zipForever(
	seqA: Sequence<A>,
	seqB: Sequence<B>,
	crossinline transform: (A, B) -> R
): Sequence<R> {
	//TODO 2.1.0 benchmark if it would make sense to have an own Sequence + Iterator for this
	val iterA = seqA.iterator()
	val iterB = seqB.iterator()

	return generateSequence {
		transform(iterA.next(), iterB.next())
	}
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
fun throwUnsupportedArgsGenerator(argsGenerator: ArgsGenerator<*>): Nothing {
	throw UnsupportedOperationException("found an ArgsGenerator ${argsGenerator::class.qualifiedName} but we don't know how to treat it, please open a feature request: ${FEATURE_REQUEST_URL}&title=Allow%20custom%20ArgsGenerator")
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
fun throwDontKnowHowToConvertToArgsGenerator(notAnArgsGenerator: Any?): Nothing {
	val representation = notAnArgsGenerator?.let { it::class.qualifiedName } ?: "null"
	throw UnsupportedOperationException("don't know how to convert $representation into an ArgsGenerator (note that you cannot mix generators and raw values), please open a feature request: ${FEATURE_REQUEST_URL}&title=Convert%20${representation}%20to%20ArgsGenerator")
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
fun throwMaterialisingSemiOrderedArgsGeneratorNotSupported(): Nothing {
	throw UnsupportedOperationException("Materialising SemiOrderedArgsGenerator is not supported out of the box to prevent bugs in the test setup")
}

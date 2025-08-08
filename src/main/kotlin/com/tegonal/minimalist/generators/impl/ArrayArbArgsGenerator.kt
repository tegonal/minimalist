package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ArrayArbArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	seedBaseOffset: Int,
	arr: Array<out T>
) : IntFromUntilArbArgsGenerator<T>(componentFactoryContainer, seedBaseOffset, from = 0, arr.size, { arr[it] })

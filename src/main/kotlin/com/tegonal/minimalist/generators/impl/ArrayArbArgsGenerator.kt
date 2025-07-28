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
    arr: Array<out T>
) : IntFromUntilArbArgsGenerator<T>(componentFactoryContainer, 0, arr.size, { arr[it] })

package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ListArbArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	list: List<T>
) : IntFromUntilArbArgsGenerator<T>(componentFactoryContainer, 0, list.size, { list[it] })

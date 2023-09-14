package com.tegonal.minimalist

import org.junit.jupiter.params.provider.Arguments

/**
 * Represents the top-interface of arguments-representations such as [Args1], [Args2] and so on.
 *
 * @since 0.1.0
 */
interface Args : Arguments {
    /**
     * Extension point for things like [Args.of][Args.Companion.of].
     */
    companion object
}

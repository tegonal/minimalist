package com.tegonal.minimalist.config.impl

abstract class MinimalistConfigException(message: String, cause: Throwable?) : RuntimeException(message, cause)
class MinimalistParseException(message: String, cause: Throwable? = null) : MinimalistConfigException(message, cause)
class MinimalistDeadlineException(message: String) : MinimalistConfigException(message, null)

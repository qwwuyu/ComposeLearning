package com.qwwuyu.base.utils

expect object WLog {
    fun v(contents: Any)

    fun d(contents: Any)

    fun i(contents: Any)

    fun w(contents: Any)

    fun e(contents: Any)

    fun a(contents: Any)

    fun v(tag: String?, format: String, vararg args: Any?)

    fun d(tag: String?, format: String, vararg args: Any?)

    fun i(tag: String?, format: String, vararg args: Any?)

    fun w(tag: String?, format: String, vararg args: Any?)

    fun e(tag: String?, format: String, vararg args: Any?)

    fun a(tag: String?, format: String, vararg args: Any?)

    fun json(type: Int, tag: String?, contents: String)

    fun xml(type: Int, tag: String?, contents: String)

    fun onError(e: Throwable?, flag: Int = -1)

    fun printStackTrace(e: Throwable?)

    fun logError(e: Throwable?)

    class Builder {
        fun enable(): Builder

        fun setLogDir(dir: String?): Builder

        fun setLogTag(tag: String?): Builder

        fun enableLogHead(enable: Boolean): Builder

        fun enableLogBorder(enable: Boolean): Builder

        fun setLogFilter(level: Int): Builder

        fun setHeadSep(head_sep: String): Builder
    }
}
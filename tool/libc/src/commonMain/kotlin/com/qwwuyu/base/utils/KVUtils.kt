package com.qwwuyu.base.utils

/**
 * KV数据操作工具类
 */
expect object KVUtils {
    fun setValue(key: String, value: String)

    fun setValue(key: String, value: Int)

    fun setValue(key: String, value: Float)

    fun setValue(key: String, value: Boolean)

    fun setValue(key: String, value: Long)

    fun setValue(key: String, value: Any)

    fun getValue(key: String, value: String): String

    fun getValue(key: String, value: Int): Int

    fun getValue(key: String, value: Float): Float

    fun getValue(key: String, value: Boolean): Boolean

    fun getValue(key: String, value: Long): Long

    inline fun <reified T : Any> getValue(key: String): T?

    fun clearKey(key: String)

    fun clear()
}
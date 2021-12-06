package com.qwwuyu.base.utils

expect class Class<T>

expect object GsonUtil {
    fun toJson(obj: Any): String

    fun <T> fromJson(result: String, clazz: Class<T>): T?
}
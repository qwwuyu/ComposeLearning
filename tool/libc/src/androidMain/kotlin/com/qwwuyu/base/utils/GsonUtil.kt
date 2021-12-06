package com.qwwuyu.base.utils

import com.qwwuyu.base.gson.GsonHelper

actual typealias Class<T> = java.lang.Class<T>

actual object GsonUtil {
    actual fun toJson(obj: Any): String {
        return GsonHelper.toJson(obj)
    }

    actual fun <T> fromJson(result: String, clazz: Class<T>): T? {
        return GsonHelper.fromJson(result, clazz)
    }
}
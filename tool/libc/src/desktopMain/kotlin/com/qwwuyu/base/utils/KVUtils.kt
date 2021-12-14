package com.qwwuyu.base.utils

import com.qwwuyu.base.dataDir
import com.qwwuyu.base.gson.GsonHelper
import java.io.File

/**
 * KV数据操作工具类
 */
actual object KVUtils {
    init {
        FileKV.init(File(dataDir(), "default.properties"))
    }

    /* ======================== set ======================== */
    actual fun setValue(key: String, value: String) {
        FileKV.putProperty(key, value)
    }

    actual fun setValue(key: String, value: Int) {
        FileKV.putProperty(key, value.toString())
    }

    actual fun setValue(key: String, value: Float) {
        FileKV.putProperty(key, value.toString())
    }

    actual fun setValue(key: String, value: Boolean) {
        FileKV.putProperty(key, value.toString())
    }

    actual fun setValue(key: String, value: Long) {
        FileKV.putProperty(key, value.toString())
    }

    actual fun setValue(key: String, value: Any) {
        FileKV.putProperty(key, GsonHelper.toJson(value))
    }

    /* ======================== get ======================== */
    actual fun getValue(key: String, value: String): String {
        return FileKV.getProperty(key) ?: value
    }

    actual fun getValue(key: String, value: Int): Int {
        return FileKV.getProperty(key)?.toInt() ?: value
    }

    actual fun getValue(key: String, value: Float): Float {
        return FileKV.getProperty(key)?.toFloat() ?: value
    }

    actual fun getValue(key: String, value: Boolean): Boolean {
        return FileKV.getProperty(key)?.toBoolean() ?: value
    }

    actual fun getValue(key: String, value: Long): Long {
        return FileKV.getProperty(key)?.toLong() ?: value
    }

    actual inline fun <reified T : Any> getValue(key: String): T? {
        val objectString = FileKV.getProperty(key)
        return try {
            GsonHelper.fromJson(objectString!!, T::class.java)
        } catch (e: Exception) {
            null
        }
    }

    fun <T> getValue(key: String, clazz: Class<T>): T? {
        val objectString = FileKV.getProperty(key)
        return try {
            GsonHelper.fromJson(objectString!!, clazz)
        } catch (e: Exception) {
            null
        }
    }

    /* ======================== remove ======================== */
    actual fun clearKey(key: String) {
        FileKV.clearKey(key)
    }

    actual fun clear() {
        FileKV.clear()
    }
}
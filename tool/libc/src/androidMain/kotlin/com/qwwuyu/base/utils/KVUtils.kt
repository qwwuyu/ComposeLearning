package com.qwwuyu.base.utils

import android.content.Context
import com.qwwuyu.base.LibApplication
import com.qwwuyu.base.gson.GsonHelper
import com.qwwuyu.base.gson.GsonHelper.toJson

/**
 * sp数据操作工具类
 */
actual object KVUtils {
    val sp = LibApplication.context.getSharedPreferences("default", Context.MODE_PRIVATE)

    /* ======================== set ======================== */
    actual fun setValue(key: String, value: String) {
        sp.edit().putString(key, value).apply()
    }

    actual fun setValue(key: String, value: Int) {
        sp.edit().putInt(key, value).apply()
    }

    actual fun setValue(key: String, value: Float) {
        sp.edit().putFloat(key, value).apply()
    }

    actual fun setValue(key: String, value: Boolean) {
        sp.edit().putBoolean(key, value).apply()
    }

    actual fun setValue(key: String, value: Long) {
        sp.edit().putLong(key, value).apply()
    }

    actual fun setValue(key: String, value: Any) {
        val objectString = toJson(value)
        sp.edit().putString(key, objectString).apply()
    }

    /* ======================== get ======================== */
    actual fun getValue(key: String, value: String): String {
        return sp.getString(key, value)!!
    }

    actual fun getValue(key: String, value: Int): Int {
        return sp.getInt(key, value)
    }

    actual fun getValue(key: String, value: Float): Float {
        return sp.getFloat(key, value)
    }

    actual fun getValue(key: String, value: Boolean): Boolean {
        return sp.getBoolean(key, value)
    }

    actual fun getValue(key: String, value: Long): Long {
        return sp.getLong(key, value)
    }

    actual inline fun <reified T : Any> getValue(key: String): T? {
        val objectString = sp.getString(key, null)
        return try {
            GsonHelper.fromJson(objectString!!, T::class.java)
        } catch (e: Exception) {
            null
        }
    }

    /* ======================== remove ======================== */
    actual fun clearKey(key: String) {
        sp.edit().remove(key).apply()
    }

    actual fun clear() {
        sp.edit().clear().apply()
    }
}
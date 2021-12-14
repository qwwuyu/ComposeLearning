package com.qwwuyu.base.utils

import java.io.File
import java.util.*

object FileKV {
    private lateinit var prop: Properties
    private lateinit var file: File

    fun init(_file: File) {
        prop = Properties()
        file = _file
        file.checkCreate()
        if (!file.isFile) throw RuntimeException("Properties create error")
        try {
            file.inputStream().use { prop.load(it) }
        } catch (e: Exception) {
            file.deleteCreate()
        }
    }

    fun getProperty(key: String): String? {
        return prop.getProperty(key)
    }

    fun setProperty(key: String, value: String) {
        prop.setProperty(key, value)
    }

    fun putProperty(key: String, value: String) {
        setProperty(key, value)
        store()
    }

    fun clearKey(key: String) {
        prop.remove(key)
        store()
    }

    fun clear() {
        prop.clear()
        store()
    }

    fun store() {
        file.outputStream().use { prop.store(it, null) }
    }
}

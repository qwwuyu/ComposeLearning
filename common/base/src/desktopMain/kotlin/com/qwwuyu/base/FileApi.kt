package com.qwwuyu.base

import java.io.File

actual fun cacheDir(): String {
    val resourcesDir = File(System.getProperty("user.dir"), "cache")
    if (!resourcesDir.exists()) resourcesDir.mkdir()
    return resourcesDir.absolutePath
}
package com.qwwuyu.base

import java.io.File

actual fun cacheDir(): String {
    val resourcesDir = File(System.getProperty("user.dir"), "cache")
    return resourcesDir.absolutePath
}
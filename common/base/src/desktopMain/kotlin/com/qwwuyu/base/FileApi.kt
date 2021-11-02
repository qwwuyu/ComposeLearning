package com.qwwuyu.base

import java.io.File

actual fun cacheDir(): String {
    val resourcesDir = File(System.getProperty("compose.application.resources.dir"))
    return resourcesDir.absolutePath
}
package com.qwwuyu.base

import com.qwwuyu.base.ext.letIf
import java.io.File

const val PROJECT_NAME = "com.qwwuyu.compose"
private var dataDir: File? = null

actual fun dataDir(): String {
    dataDir?.letIf({ it.exists() }) { return it.absolutePath }
    synchronized(PROJECT_NAME) {
        var dir = File(System.getProperty("user.home"), PROJECT_NAME)
        if (dir.isDirectory) {
            dataDir = dir
            return@synchronized
        }
        dir = File(System.getProperty("user.dir"), PROJECT_NAME)
        if (dir.isDirectory || dir.mkdirs()) {
            dataDir = dir
            return@synchronized
        }
        dir = File(System.getProperty("user.home"), PROJECT_NAME)
        if (dir.isDirectory || dir.mkdirs()) {
            dataDir = dir
            return@synchronized
        }
        dir = File(System.getProperty("java.io.tmpdir"), PROJECT_NAME)
        if (dir.isDirectory || dir.mkdirs()) {
            dataDir = dir
        }
    }
    return dataDir!!.absolutePath
}

actual fun cacheDir(): String {
    val resourcesDir = File(System.getProperty("java.io.tmpdir"), PROJECT_NAME)
    if (resourcesDir.isDirectory || resourcesDir.mkdirs()) return resourcesDir.absolutePath
    return dataDir()
}
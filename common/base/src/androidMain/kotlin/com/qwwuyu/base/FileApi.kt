package com.qwwuyu.base

actual fun cacheDir(): String {
    return LibApplication.context.cacheDir.absolutePath
}
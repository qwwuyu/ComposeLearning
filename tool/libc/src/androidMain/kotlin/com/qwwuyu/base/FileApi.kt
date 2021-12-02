package com.qwwuyu.base

import java.io.File
import java.io.PrintWriter
import java.io.StringWriter
import java.text.SimpleDateFormat
import java.util.*

actual fun dataDir(): String {
    return LibApplication.context.filesDir.absolutePath
}

actual fun cacheDir(): String {
    return LibApplication.context.cacheDir.absolutePath
}

fun handleError() {
    val exceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
    Thread.setDefaultUncaughtExceptionHandler { t, e ->
        val format = SimpleDateFormat("yyyy_MM_dd HH_mm_ss", Locale.getDefault())
        val date = format.format(System.currentTimeMillis())
        val sw = StringWriter()
        e.printStackTrace(PrintWriter(sw))
        val errorDir = File(dataDir(), "error")
        if (!errorDir.exists()) errorDir.mkdir()
        val errorFile = File(errorDir, "${date}.log")
        errorFile.printWriter().use { it.print(sw.toString()) }
        exceptionHandler?.uncaughtException(t, RuntimeException("运行出错，请查看错误文件：${errorFile.path}", e))
    }
}
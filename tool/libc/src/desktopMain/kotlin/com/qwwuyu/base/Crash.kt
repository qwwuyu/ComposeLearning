package com.qwwuyu.base

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.io.File
import java.io.PrintWriter
import java.io.StringWriter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.system.exitProcess

val errMsg = mutableStateOf<Throwable?>(null)
var logMsg = ""

fun handleErrorApplication(content: @Composable (ApplicationScope.() -> Unit)) =
    application {
        handleError()
        content()
        errorWindow()
    }

@Composable
fun handleError() {
    val exceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
    Thread.setDefaultUncaughtExceptionHandler { t, e ->
        val format = SimpleDateFormat("yyyy_MM_dd HH_mm_ss", Locale.getDefault())
        val date = format.format(System.currentTimeMillis())

        val errorDir = File(dataDir(), "error")
        if (!errorDir.exists()) errorDir.mkdir()
        val errorFile = File(errorDir, "${date}.log")
        errorFile.printWriter().use { it.print(e.msgStr()) }
        logMsg = "，日志路径：${errorFile.path}"
        errMsg.value = e
        exceptionHandler?.uncaughtException(t, RuntimeException("运行出错，请查看错误文件：${errorFile.path}", e))
    }
}

@Composable
fun ApplicationScope.errorWindow() {
    if (errMsg.value != null) {
        Window(
            onCloseRequest = {
                exitApplication()
                exitProcess(0)
            },
            title = "程序发生错误${logMsg}",
            icon = painterResource("drawable-nodpi/icon.png"),
        ) {
            Text(errMsg.value!!.msgStr())
        }
    }
}

private fun Throwable.msgStr(): String {
    val sw = StringWriter()
    this.printStackTrace(PrintWriter(sw))
    return sw.toString()
}
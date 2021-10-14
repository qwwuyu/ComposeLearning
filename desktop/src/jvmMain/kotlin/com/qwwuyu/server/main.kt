package com.qwwuyu.server

import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowSize
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.qwwuyu.server.compose.platform.imageResource
import com.qwwuyu.server.compose.ui.MainView

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "qwwuyu",
        icon = BitmapPainter(imageResource("drawable-nodpi/icon.png")),
        state = WindowState(size = WindowSize(800.dp, 600.dp))
    ) {
        MainView()
    }
}
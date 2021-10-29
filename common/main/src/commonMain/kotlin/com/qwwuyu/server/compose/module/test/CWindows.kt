package com.qwwuyu.server.compose.module.test

import androidx.compose.runtime.Composable
import com.qwwuyu.server.compose.platform.WinApi

class CWindows {
    companion object {
        @Composable
        fun main() {
            CWindowsMain()
        }
    }
}

@Composable
fun CWindowsMain() {
    WinApi()
}
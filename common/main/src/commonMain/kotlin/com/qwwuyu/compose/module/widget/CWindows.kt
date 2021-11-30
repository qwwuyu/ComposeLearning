package com.qwwuyu.compose.module.widget

import androidx.compose.runtime.Composable
import com.qwwuyu.compose.platform.WinApi

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
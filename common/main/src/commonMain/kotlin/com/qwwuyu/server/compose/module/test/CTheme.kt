package com.qwwuyu.server.compose.module.test

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class CTheme {
    companion object {
        @Composable
        fun main() {
            CThemeMain()
        }
    }
}

@Composable
fun CThemeMain() {
    Text("CTheme")
}
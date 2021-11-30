package com.qwwuyu.compose.module.widget

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
package com.qwwuyu.server.compose.module.test

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class CNav {
    companion object {
        @Composable
        fun main() {
            CNavMain()
        }
    }
}

@Composable
fun CNavMain() {
    Text("CNav")
}
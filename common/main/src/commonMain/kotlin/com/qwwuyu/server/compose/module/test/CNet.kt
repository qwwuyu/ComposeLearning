package com.qwwuyu.server.compose.module.test

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class CNet {
    companion object {
        @Composable
        fun main() {
            CNetMain()
        }
    }
}

@Composable
fun CNetMain() {
    Text("CNet")
}
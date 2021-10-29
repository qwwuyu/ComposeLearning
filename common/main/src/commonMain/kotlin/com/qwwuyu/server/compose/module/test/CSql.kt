package com.qwwuyu.server.compose.module.test

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class CSql {
    companion object {
        @Composable
        fun main() {
            CSqlMain()
        }
    }
}

@Composable
fun CSqlMain() {
    Text("CSql")
}
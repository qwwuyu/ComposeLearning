package com.qwwuyu.server.compose.module.test

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class CText {
    companion object {
        val NAME = CText::class.simpleName ?: ""

        @Composable
        fun main() {
            CTextMain()
        }
    }
}

@Composable
fun CTextMain() {
    Text("Text")
}
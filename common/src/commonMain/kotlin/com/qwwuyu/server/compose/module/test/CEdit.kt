package com.qwwuyu.server.compose.module.test

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class CEdit {
    companion object {
        @Composable
        fun main() {
            CEditMain()
        }
    }
}

@Composable
fun CEditMain() {
    BasicTextField("", { }, enabled = true, modifier = Modifier.fillMaxSize())
}
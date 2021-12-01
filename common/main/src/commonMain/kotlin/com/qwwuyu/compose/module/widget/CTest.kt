package com.qwwuyu.compose.module.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class CTest {
    companion object {
        @Composable
        fun main() {
            CTestMain()
        }
    }
}

@Composable
fun CTestMain() {
    Column(Modifier.fillMaxSize()) {
        throw RuntimeException("123")
    }
}
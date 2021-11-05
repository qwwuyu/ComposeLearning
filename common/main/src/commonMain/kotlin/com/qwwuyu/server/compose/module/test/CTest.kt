package com.qwwuyu.server.compose.module.test

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

    }
}
package com.qwwuyu.server.compose.module.test

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class CImage {
    companion object {
        @Composable
        fun main() {
            CImageMain()
        }
    }
}

@Composable
fun CImageMain() {
    Text("CImage")
}
package com.qwwuyu.compose.module.widget

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
package com.qwwuyu.server.compose.module.test

import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.runtime.Composable

class CFile {
    companion object {
        val NAME = CText::class.simpleName ?: ""

        @Composable
        fun main() {
            CFileMain()
        }
    }
}

@Composable
fun CFileMain() {
}
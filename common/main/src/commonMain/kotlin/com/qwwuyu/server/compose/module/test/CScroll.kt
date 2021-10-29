package com.qwwuyu.server.compose.module.test

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class CScroll {
    companion object {
        @Composable
        fun main() {
            Column(Modifier.fillMaxSize()) {
                val text = SelectTab(listOf("vertical", "horizontal"))
                Box(Modifier.weight(1f)) {
                    when (text) {
                        "vertical" -> Vertical()
                        "horizontal" -> Horizontal()
                    }
                }
            }
        }
    }
}

@Composable
fun Vertical() {
    Column(Modifier.verticalScroll(rememberScrollState()).fillMaxSize()) {
        repeat(100) { Text("Text$it") }
    }
}

@Composable
fun Horizontal() {
    Row(Modifier.horizontalScroll(rememberScrollState()).fillMaxSize()) {
        repeat(100) { Text("Text$it") }
    }
}
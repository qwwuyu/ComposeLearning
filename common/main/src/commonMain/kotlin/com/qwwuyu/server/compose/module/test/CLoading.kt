package com.qwwuyu.server.compose.module.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class CLoading {
    companion object {
        @Composable
        fun main() {
            CLoadingMain()
        }
    }
}

@Composable
fun CLoadingMain() {
    Column(modifier = Modifier.verticalScroll(state = rememberScrollState())) {
        CircularProgressIndicator()
        CircularProgressIndicator(strokeWidth = 8.dp)
        LinearProgressIndicator()
    }
}

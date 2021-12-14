package com.qwwuyu.compose.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun SelectTab(textList: List<String>): String {
    var text by remember { mutableStateOf("") }
    Row(Modifier.horizontalScroll(rememberScrollState())) {
        textList.forEach {
            val modifier = if (text == it) Modifier
            else Modifier.background(color = MaterialTheme.colors.primaryVariant)
            Button(onClick = { text = it }, modifier = modifier) { Text(it) }
        }
    }
    return text
}
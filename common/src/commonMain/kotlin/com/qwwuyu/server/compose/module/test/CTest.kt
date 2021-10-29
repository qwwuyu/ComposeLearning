package com.qwwuyu.server.compose.module.test

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

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
        var state by remember { mutableStateOf(true) }
        var text1Visibility = mutableStateOf(true)
        Text("Click", modifier = Modifier.clickable { state = !state })
        if (state) {
            var text by remember { mutableStateOf(TextFieldValue("")) }
            TextField(
                value = text,
                onValueChange = { newValue -> text = newValue },
                modifier = Modifier.padding(8.dp).fillMaxWidth(),
                label = { Text("label") },
                placeholder = { Text("placeholder") }
            )
        }
    }


}
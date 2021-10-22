package com.qwwuyu.server.compose.module.test

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TestCompose(type: String) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.primary) {
        when (type) {
            CText.NAME -> CText.main()
            CScaffold.NAME -> CScaffold.main()
            else -> TestScreenCompose()
        }
    }
}

@Composable
fun TestScreenCompose() {
    Column {
        TestTextCompose(CText.NAME)
        TestTextCompose(CScaffold.NAME)
    }
}

@Composable
fun TestTextCompose(title: String) {
    Box(modifier = Modifier.fillMaxWidth().height(48.dp).clickable {

    }) {
        Text(text = title, modifier = Modifier.align(Alignment.CenterStart).padding(16.dp, 0.dp))
    }
}
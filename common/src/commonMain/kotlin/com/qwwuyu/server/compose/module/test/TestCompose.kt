package com.qwwuyu.server.compose.module.test

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qwwuyu.server.compose.nav.Nav
import com.qwwuyu.server.compose.nav.rememberNav

@Composable
fun TestCompose(type: String) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        when (type) {
            CText.NAME -> CText.main()
            CScaffold.NAME -> CScaffold.main()
            CScroll.NAME -> CScroll.main()
            else -> TestScreenCompose()
        }
    }
}

@Composable
fun TestScreenCompose() {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        TestTextCompose(CText.NAME)
        TestTextCompose(CScaffold.NAME)
        TestTextCompose(CScroll.NAME)
    }
}

@Composable
fun TestTextCompose(title: String) {
    val nav = rememberNav()
    Box(modifier = Modifier.fillMaxWidth().height(48.dp).clickable {
        nav.nav(Nav.PATH_TEST, title)
    }) {
        Text(text = title, modifier = Modifier.align(Alignment.CenterStart).padding(16.dp, 0.dp))
    }
}

@Composable
fun SelectTab(textList: List<String>): String {
    var text by remember { mutableStateOf("vertical") }
    Row(Modifier.horizontalScroll(rememberScrollState())) {
        textList.forEach {
            val modifier = if (text == it) Modifier else Modifier
            Button(onClick = { text = it }, modifier = modifier) { Text(it) }
        }
    }
    return text
}
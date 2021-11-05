package com.qwwuyu.server.compose.module.test

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
            "CTest" -> CTest.main()
            "CWindows" -> CWindows.main()
            "CText" -> CText.main()
            "CEdit" -> CEdit.main()
            "CButton" -> CButton.main()
            "CScaffold" -> CScaffold.main()
            "CScroll" -> CScroll.main()
            "CLoading" -> CLoading.main()
            "CToggle" -> CToggle.main()
            "CView" -> CView.main()
            "CTheme" -> CTheme.main()
            "CNet" -> CNet.main()
            "CImage" -> CImage.main()
            "CNav" -> CNav.main()
            "CSql" -> CSql.main()
            "CKV" -> CKV.main()
            else -> TestScreenCompose()
        }
    }
}

@Composable
fun TestScreenCompose() {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        for (text in arrayOf(
            "CTest",
            "CWindows",
            "CText",
            "CEdit",
            "CButton",
            "CScaffold",
            "CScroll",
            "CLoading",
            "CToggle",
            "CView",
            "CTheme",
            "CNet",
            "CImage",
            "CNav",
            "CSql",
            "CKV",
        )) {
            TestTextCompose(text)
        }
    }
}

@Composable
fun TestTextCompose(title: String) {
    val nav = rememberNav()
    Box(modifier = Modifier.fillMaxWidth().height(48.dp).clickable {
        nav.nav(Nav.Path.Test(title))
    }) {
        Text(text = title, modifier = Modifier.align(Alignment.CenterStart).padding(16.dp, 0.dp))
    }
}

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
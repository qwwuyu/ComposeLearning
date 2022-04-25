package com.qwwuyu.compose.module.widget

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.qwwuyu.base.platform.HorizontalScrollbar
import com.qwwuyu.base.platform.MARGIN_SCROLLBAR
import com.qwwuyu.base.utils.WLog
import com.qwwuyu.widget.MWidget

@Composable
fun WidgetContent(component: MWidget) {
    val model by component.models.subscribeAsState()
    Column {
        TopAppBar(
            title = { Text(model.type) },
            navigationIcon = {
                IconButton(onClick = component::finish) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        )

        Box(Modifier.weight(1F)) {
            when (model.type) {
                "CSystem" -> CWindows.main()
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
                "CompositionLocalProvider" -> CLocal.main()
                "CCrash" -> CTest.main()
                else -> TestScreenCompose(component::onItemClicked, component::onTKV)
            }
        }
    }
}

@Composable
fun TestScreenCompose(onItemClicked: (type: String) -> Unit, onTKV: () -> Unit) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        TestTextCompose("router,database,key-value") { onTKV() }
        for (text in arrayOf(
            "CSystem",
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
            "CompositionLocalProvider",
            "CCrash",
        )) {
            TestTextCompose(text, onItemClicked)
        }
    }
}

@Composable
fun TestTextCompose(title: String, onItemClicked: (type: String) -> Unit) {
    Box(modifier = Modifier.fillMaxWidth().height(48.dp).clickable {
        WLog.i("TestTextCompose=${title}")
        onItemClicked(title)
    }) {
        Text(text = title, modifier = Modifier.align(Alignment.CenterStart).padding(16.dp, 0.dp))
    }
}

@Composable
fun SelectTab(textList: List<String>): String {
    var text by remember { mutableStateOf("") }
    Box {
        val scrollState = rememberScrollState()
        Row(Modifier.horizontalScroll(scrollState).fillMaxSize().padding(top = MARGIN_SCROLLBAR)) {
            textList.forEach {
                val modifier = if (text == it) Modifier
                else Modifier.background(color = MaterialTheme.colors.primaryVariant)
                Button(onClick = { text = it }, modifier = modifier) { Text(it) }
            }
        }
        HorizontalScrollbar(
            Modifier.align(Alignment.TopCenter),
            scrollState
        )
    }
    return text
}
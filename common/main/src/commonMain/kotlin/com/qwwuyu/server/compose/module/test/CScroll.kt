package com.qwwuyu.server.compose.module.test

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.qwwuyu.server.compose.platform.HorizontalScrollbar
import com.qwwuyu.server.compose.platform.MARGIN_SCROLLBAR
import com.qwwuyu.server.compose.platform.VerticalScrollbar
import androidx.compose.foundation.lazy.items

class CScroll {
    companion object {
        @Composable
        fun main() {
            Column(Modifier.fillMaxSize()) {
                val text = SelectTab(listOf("vertical", "horizontal", "LazyList"))
                Box(Modifier.weight(1f)) {
                    when (text) {
                        "vertical" -> Vertical()
                        "horizontal" -> Horizontal()
                        "LazyList" -> LazyList()
                    }
                }
            }
        }
    }
}

@Composable
fun Vertical() {
    Box {
        val scrollState = rememberScrollState()

        Column(Modifier.verticalScroll(scrollState).fillMaxSize()) {
            repeat(100) { Text("Text$it") }
        }

        VerticalScrollbar(
            Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            scrollState
        )
    }
}

@Composable
fun Horizontal() {
    Box {
        val scrollState = rememberScrollState()

        Row(Modifier.horizontalScroll(scrollState).fillMaxSize().padding(top = MARGIN_SCROLLBAR)) {
            repeat(100) { Text("Text$it") }
        }

        HorizontalScrollbar(
            Modifier.align(Alignment.TopCenter),
            scrollState
        )
    }
}


@Composable
fun LazyList() {
    Box {
        val listState = rememberLazyListState()
        val list = (0..100).toList()

        LazyColumn(state = listState) {
            items(list) {
                Text("item:$it")
                Divider()
            }
        }

        VerticalScrollbar(
            Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            listState
        )
    }
}
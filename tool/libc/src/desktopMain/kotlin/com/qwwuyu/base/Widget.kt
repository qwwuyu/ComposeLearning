package com.qwwuyu.base

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.qwwuyu.base.platform.VerticalScrollbar

@Composable
actual fun ColumnScrollbar(
    modifier: Modifier,
    verticalArrangement: Arrangement.Vertical,
    horizontalAlignment: Alignment.Horizontal,
    content: @Composable ColumnScope.() -> Unit
) {
    Box {
        val scrollState = rememberScrollState()
        Column(
            modifier.fillMaxSize().verticalScroll(scrollState),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            content = content
        )
        VerticalScrollbar(
            Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            scrollState
        )
    }
}
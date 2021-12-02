package com.qwwuyu.base.platform

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

actual val MARGIN_SCROLLBAR: Dp = 8.dp

@Composable
actual fun VerticalScrollbar(
    modifier: Modifier,
    scrollState: ScrollState
) = androidx.compose.foundation.VerticalScrollbar(
    rememberScrollbarAdapter(scrollState),
    modifier
)

@Composable
actual fun VerticalScrollbar(
    modifier: Modifier,
    scrollState: LazyListState
) = androidx.compose.foundation.VerticalScrollbar(
    rememberScrollbarAdapter(scrollState),
    modifier
)

@Composable
actual fun HorizontalScrollbar(
    modifier: Modifier,
    scrollState: LazyListState
) = androidx.compose.foundation.HorizontalScrollbar(
    rememberScrollbarAdapter(scrollState),
    modifier
)

@Composable
actual fun HorizontalScrollbar(
    modifier: Modifier,
    scrollState: ScrollState
) = androidx.compose.foundation.HorizontalScrollbar(
    rememberScrollbarAdapter(scrollState),
    modifier
)
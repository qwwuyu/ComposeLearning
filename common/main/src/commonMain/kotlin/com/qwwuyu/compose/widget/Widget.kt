package com.qwwuyu.compose.widget

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable

@Composable
expect fun MultiPane(content: PaneScope.() -> Unit)

@Composable
expect fun WDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable (ColumnScope.() -> Unit)
)

@Composable
expect fun WDropdownMenuItem(
    onClick: () -> Unit,
    content: @Composable (RowScope.() -> Unit)
)
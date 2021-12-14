package com.qwwuyu.compose.widget

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun MultiPane(content: PaneScope.() -> Unit) {
    val scaffoldState = rememberScaffoldState()
    with(PaneScopeImpl().apply(content)) {
        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier,
            drawerContent = { first() }
        ) {
            second()
        }
    }
}

@Composable
actual fun WDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable (ColumnScope.() -> Unit)
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        content = content,
    )
}

@Composable
actual fun WDropdownMenuItem(
    onClick: () -> Unit,
    content: @Composable (RowScope.() -> Unit)
) {
    DropdownMenuItem(
        onClick = onClick,
        content = content,
    )
}
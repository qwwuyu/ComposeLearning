package com.qwwuyu.compose.widget

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
actual fun MultiPane(content: PaneScope.() -> Unit) {
    val scaffoldState = rememberScaffoldState(drawerState = rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val ctrl: (open: Boolean) -> Unit = { open ->
        scope.launch {
            scaffoldState.drawerState.apply {
                if (open) open() else close()
            }
        }
    }
    scope.launch {
        scaffoldState.drawerState.apply { open() }
    }
    with(PaneScopeImpl(ctrl = ctrl).apply(content)) {
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
package com.qwwuyu.compose.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import org.jetbrains.compose.splitpane.HorizontalSplitPane
import org.jetbrains.compose.splitpane.rememberSplitPaneState

@OptIn(ExperimentalSplitPaneApi::class)
@Composable
actual fun MultiPane(content: PaneScope.() -> Unit) {
    val splitPaneState = rememberSplitPaneState(moveEnabled = false)
    Box(Modifier.fillMaxSize()) {
        with(PaneScopeImpl().apply(content)) {
            HorizontalSplitPane(splitPaneState = splitPaneState) {
                first(minSize = 320.dp) { first() }
                second { second() }
            }
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
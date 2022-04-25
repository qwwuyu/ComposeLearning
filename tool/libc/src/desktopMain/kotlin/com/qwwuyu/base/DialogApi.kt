package com.qwwuyu.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberDialogState
import com.qwwuyu.base.platform.VerticalScrollbar

@Composable
actual fun BaseDialog(onDismiss: () -> Unit, content: @Composable () -> Unit) {
    val dialogState = rememberDialogState(position = WindowPosition(Alignment.Center), size = DpSize(360.dp, 480.dp))
    Dialog(
        onCloseRequest = onDismiss,
//            undecorated = true,
        title = "",
        state = dialogState,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Surface(
                color = Color(0x00ffffff),
                contentColor = Color(0x00ffffff),
                modifier = Modifier.align(Alignment.Center)
            ) {
                content()
            }
        }
    }
}

@Composable
actual fun SystemDialog(onDismiss: () -> Unit, content: @Composable () -> Unit) {
    val dialogState = rememberDialogState(position = WindowPosition(Alignment.Center), size = DpSize(360.dp, 480.dp))
    Dialog(
        onCloseRequest = onDismiss,
        title = "",
        state = dialogState,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            val scrollState = rememberScrollState()
            Column(
                Modifier.fillMaxSize().verticalScroll(scrollState),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.weight(1f))
                content()
                Spacer(Modifier.weight(1f))
            }
            VerticalScrollbar(
                Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                scrollState
            )
        }
    }
}

@Composable
actual fun SelectDialog(onDismiss: (select: Int?) -> Unit, selects: List<String>) {
    val height: Dp = if (selects.size > 5) (5.5f * 48).dp else (selects.size * 48).dp
    val dialogState = rememberDialogState(
        position = WindowPosition(Alignment.Center),
        size = DpSize(360.dp, height + 40.dp)
    )
    Dialog(
        onCloseRequest = { onDismiss(null) },
        title = "",
        state = dialogState,
    ) {
        Box(modifier = Modifier.fillMaxSize().height(height)) {
            val scrollState = rememberScrollState()
            Column(
                Modifier.fillMaxSize().verticalScroll(scrollState),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.weight(1f))
                selects.forEachIndexed { index, s ->
                    Box(modifier = Modifier.fillMaxWidth().height(48.dp).clickable {
                        onDismiss(index)
                    }) {
                        Text(text = s, modifier = Modifier.align(Alignment.CenterStart).padding(16.dp, 0.dp))
                    }
                }
                Spacer(Modifier.weight(1f))
            }
            VerticalScrollbar(
                Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                scrollState
            )
        }
    }
}
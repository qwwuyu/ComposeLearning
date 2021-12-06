package com.qwwuyu.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberDialogState

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
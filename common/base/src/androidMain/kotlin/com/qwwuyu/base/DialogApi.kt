package com.qwwuyu.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
actual fun BaseDialog(onDismiss: () -> Unit, content: @Composable () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        content()
    }
}
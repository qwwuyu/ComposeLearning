package com.qwwuyu.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
actual fun BaseDialog(onDismiss: () -> Unit, content: @Composable () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        content()
    }
}

@Composable
actual fun SystemDialog(onDismiss: () -> Unit, content: @Composable () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.padding(0.dp).fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp
        ) {
            Box(Modifier.padding(16.dp)) {
                content()
            }
        }
    }
}
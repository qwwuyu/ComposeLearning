package com.qwwuyu.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.qwwuyu.base.platform.VerticalScrollbar

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

@Composable
actual fun SelectDialog(onDismiss: (select: Int?) -> Unit, selects: List<String>) {
    val height: Dp = if (selects.size > 5) (5.5f * 48).dp else (selects.size * 48).dp

    Dialog(onDismissRequest = { onDismiss(null) }) {
        Card(
            modifier = Modifier.padding(0.dp).fillMaxWidth().height(height),
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                val scrollState = rememberScrollState()
                Column(
                    Modifier.fillMaxSize().verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    selects.forEachIndexed { index, s ->
                        Box(modifier = Modifier.fillMaxWidth().height(48.dp).clickable {
                            onDismiss(index)
                        }) {
                            Text(text = s, modifier = Modifier.align(Alignment.CenterStart).padding(16.dp, 0.dp))
                        }
                    }
                }
                VerticalScrollbar(
                    Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                    scrollState
                )
            }
        }
    }
}
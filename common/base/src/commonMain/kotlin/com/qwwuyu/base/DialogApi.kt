package com.qwwuyu.base

import androidx.compose.runtime.Composable

@Composable
expect fun BaseDialog(onDismiss: () -> Unit, content: @Composable () -> Unit)
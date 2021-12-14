package com.qwwuyu.base

import androidx.compose.runtime.Composable

@Composable
expect fun BaseDialog(onDismiss: () -> Unit, content: @Composable () -> Unit)

@Composable
expect fun SystemDialog(onDismiss: () -> Unit, content: @Composable () -> Unit)
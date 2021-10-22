package com.qwwuyu.server.compose.ui

import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.runtime.Composable
import com.qwwuyu.server.compose.module.test.TestCompose
import com.qwwuyu.server.compose.theme.AppTheme

@Composable
fun MainView() {
    DisableSelection {
        AppTheme {
            TestCompose("")
        }
    }
}
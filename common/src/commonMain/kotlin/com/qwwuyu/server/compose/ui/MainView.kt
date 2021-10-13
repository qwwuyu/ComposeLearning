package com.qwwuyu.server.compose.ui

import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.qwwuyu.server.compose.theme.WidgetGalleryTheme

@Composable
fun MainView() {
    DisableSelection {
        WidgetGalleryTheme {
            Text("demo")
        }
    }
}
package com.qwwuyu.server.compose.platform

import androidx.compose.runtime.Composable
import androidx.compose.foundation.isSystemInDarkTheme as androidSystemIsInDarkTheme

@Composable
actual fun isSystemInDarkTheme(): Boolean =
    androidSystemIsInDarkTheme()
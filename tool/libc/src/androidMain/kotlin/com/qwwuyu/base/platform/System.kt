package com.qwwuyu.base.platform

import androidx.compose.runtime.Composable
import androidx.compose.foundation.isSystemInDarkTheme as androidSystemIsInDarkTheme

@Composable
actual fun isSystemInDarkTheme(): Boolean =
    androidSystemIsInDarkTheme()
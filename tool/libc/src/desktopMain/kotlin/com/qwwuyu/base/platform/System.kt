package com.qwwuyu.base.platform

import org.jetbrains.skiko.SystemTheme

actual fun isSystemInDarkTheme(): Boolean = org.jetbrains.skiko.currentSystemTheme == SystemTheme.DARK
package com.qwwuyu.base.platform

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

@Composable
actual fun imageResource(res: String): Painter = painterResource(res)

@Composable
actual fun vectorResource(res: String): Painter = painterResource(res)
package com.qwwuyu.base.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import kotlin.jvm.JvmInline

@JvmInline
value class TProvidableCompositionLocal<T> constructor(
    private val delegate: ProvidableCompositionLocal<T?> = staticCompositionLocalOf { null }
) {
    val current: T @Composable get() = delegate.current!!
    infix fun provides(value: T) = delegate provides value
}
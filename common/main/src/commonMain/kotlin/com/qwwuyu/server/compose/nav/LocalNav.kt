package com.qwwuyu.server.compose.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import kotlin.jvm.JvmInline

@Composable
fun rememberNav(): Nav = LocalNav.current

val LocalNav = NavProvidableCompositionLocal()

@JvmInline
value class NavProvidableCompositionLocal internal constructor(
    private val delegate: ProvidableCompositionLocal<Nav?> = staticCompositionLocalOf { null }
) {

    val current: Nav
        @Composable get() = delegate.current!!

    infix fun provides(value: Nav) = delegate provides value

    infix fun providesDefault(value: Nav) = delegate providesDefault value
}

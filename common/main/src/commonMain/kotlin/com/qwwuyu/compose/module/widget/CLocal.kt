package com.qwwuyu.compose.module.widget

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import kotlin.jvm.JvmInline

class CLocal {
    companion object {
        @Composable
        fun main() {
            CLocalMain()
        }
    }
}

@Composable
fun CLocalMain() {
    val nav = object : Nav {
        override fun test(msg: String) {
            println(msg)
        }
    }
    CompositionLocalProvider(LocalNav provides nav) {
        val rememberNav = rememberNav()
        Text("click", modifier = Modifier.clickable { rememberNav.test("123") })
    }
}

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

interface Nav {
    fun test(msg: String)
}
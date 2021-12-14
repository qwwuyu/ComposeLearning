package com.qwwuyu.compose.widget

import androidx.compose.runtime.Composable

interface PaneScope {
    fun first(content: @Composable () -> Unit)
    fun second(content: @Composable () -> Unit)
}

internal class PaneScopeImpl : PaneScope {
    internal lateinit var first: (@Composable () -> Unit)
        private set
    internal lateinit var second: (@Composable () -> Unit)
        private set

    override fun first(content: @Composable () -> Unit) {
        first = content
    }

    override fun second(content: @Composable () -> Unit) {
        second = content
    }
}
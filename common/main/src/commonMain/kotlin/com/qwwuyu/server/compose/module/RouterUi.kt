@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.qwwuyu.server.compose.module

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.crossfadeScale
import com.qwwuyu.router.MRouter
import com.qwwuyu.router.MRouter.Child
import com.qwwuyu.server.compose.module.home.HomeContent
import com.qwwuyu.server.compose.module.kv.KVContent

@Composable
fun RouterContent(component: MRouter) {
    Children(routerState = component.routerState, animation = crossfadeScale()) {
        when (val child = it.instance) {
            is Child.Home -> HomeContent(child.component)
            is Child.KV -> KVContent(child.component)
        }
    }
}
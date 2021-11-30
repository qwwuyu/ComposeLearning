@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.qwwuyu.compose.module

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.qwwuyu.compose.module.home.HomeContent
import com.qwwuyu.compose.module.kv.KVContent
import com.qwwuyu.compose.module.widget.WidgetContent
import com.qwwuyu.router.MRouter
import com.qwwuyu.router.MRouter.Child

@Composable
fun RouterContent(component: MRouter) {
    Children(routerState = component.routerState/*, animation = crossfadeScale()*/) {
        when (val child = it.instance) {
            is Child.Home -> HomeContent(child.component)
            is Child.KV -> KVContent(child.component)
            is Child.Widget -> WidgetContent(child.component)
        }
    }
}
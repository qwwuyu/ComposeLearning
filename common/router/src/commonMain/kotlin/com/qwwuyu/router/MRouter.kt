package com.qwwuyu.router

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.qwwuyu.home.MHome
import com.qwwuyu.tkv.MKV
import com.qwwuyu.widget.MWidget

interface MRouter {
    val routerState: Value<RouterState<*, Child>>

    sealed class Child {
        data class Home(val component: MHome) : Child()
        data class KV(val component: MKV) : Child()
        data class Widget(val component: MWidget) : Child()
    }
}
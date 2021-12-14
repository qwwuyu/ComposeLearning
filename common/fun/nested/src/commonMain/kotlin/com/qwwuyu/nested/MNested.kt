package com.qwwuyu.nested

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.qwwuyu.nested.home.MHome
import com.qwwuyu.nested.me.MMe
import com.qwwuyu.nested.msg.MMsg

interface MNested {
    val routerState: Value<RouterState<*, Child>>

    val models: Value<Model>

    fun finish()

    fun onClicked(type: Int)

    data class Model(
        val type: Int,
    )

    sealed class Output {
        object Finished : Output()
    }

    sealed class Child {
        data class Home(val component: MHome) : Child()
        data class Msg(val component: MMsg) : Child()
        data class Me(val component: MMe) : Child()
    }
}
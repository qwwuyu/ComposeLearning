package com.qwwuyu.nested.msg

import com.arkivanov.mvikotlin.core.store.Store
import com.qwwuyu.nested.msg.MsgStore.Intent
import com.qwwuyu.nested.msg.MsgStore.State

internal interface MsgStore : Store<Intent, State, Nothing> {
    sealed class Intent {
        object Clicked : Intent()
        data class TextChanged(val text: String) : Intent()
    }

    data class State(
        val text: String = "",
    )
}

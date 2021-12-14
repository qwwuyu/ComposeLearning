package com.qwwuyu.nested.me

import com.arkivanov.mvikotlin.core.store.Store
import com.qwwuyu.nested.me.MeStore.Intent
import com.qwwuyu.nested.me.MeStore.State

internal interface MeStore : Store<Intent, State, Nothing> {
    sealed class Intent {
        object Clicked : Intent()
        data class TextChanged(val text: String) : Intent()
    }

    data class State(
        val text: String = "",
    )
}

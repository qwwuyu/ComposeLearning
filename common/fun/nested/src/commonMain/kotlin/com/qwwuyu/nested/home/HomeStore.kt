package com.qwwuyu.nested.home

import com.arkivanov.mvikotlin.core.store.Store
import com.qwwuyu.nested.home.HomeStore.Intent
import com.qwwuyu.nested.home.HomeStore.State

internal interface HomeStore : Store<Intent, State, Nothing> {
    sealed class Intent {
        object Clicked : Intent()
        data class TextChanged(val text: String) : Intent()
    }

    data class State(
        val text: String = "",
    )
}

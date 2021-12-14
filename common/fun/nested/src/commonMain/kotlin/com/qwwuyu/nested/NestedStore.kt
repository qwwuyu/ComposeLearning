package com.qwwuyu.nested

import com.arkivanov.mvikotlin.core.store.Store
import com.qwwuyu.nested.NestedStore.Intent
import com.qwwuyu.nested.NestedStore.State

internal interface NestedStore : Store<Intent, State, Nothing> {
    sealed class Intent {
        data class Clicked(val type: Int) : Intent()
    }

    data class State(
        val type: Int = 0,
    )
}

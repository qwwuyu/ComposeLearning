package com.qwwuyu.widget.store

import com.arkivanov.mvikotlin.core.store.Store

internal interface WidgetStore : Store<WidgetStore.Intent, WidgetStore.State, Nothing> {
    sealed class Intent

    data class State(
        val type: String = ""
    )
}

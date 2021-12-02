package com.qwwuyu.home.store

import com.arkivanov.mvikotlin.core.store.Store
import com.qwwuyu.home.HomeItem

internal interface HomeStore : Store<HomeStore.Intent, HomeStore.State, Nothing> {
    sealed class Intent {
        object LoginItem : Intent()
    }

    data class State(
        val items: List<HomeItem> = emptyList(),
    )
}

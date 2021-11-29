package com.qwwuyu.tkv.store

import com.arkivanov.mvikotlin.core.store.Store
import com.qwwuyu.tkv.KVItem
import com.qwwuyu.tkv.store.KVStore.Intent
import com.qwwuyu.tkv.store.KVStore.State

internal interface KVStore : Store<Intent, State, Nothing> {
    sealed class Intent {
        data class DeleteItem(val key: String) : Intent()
        data class AddItem(val key: String, val value: String) : Intent()
    }

    data class State(
        val items: List<KVItem> = emptyList(),
    )
}

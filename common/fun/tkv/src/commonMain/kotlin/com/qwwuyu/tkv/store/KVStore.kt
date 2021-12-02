package com.qwwuyu.tkv.store

import com.arkivanov.mvikotlin.core.store.Store
import com.qwwuyu.tkv.AEItem
import com.qwwuyu.tkv.KVItem
import com.qwwuyu.tkv.store.KVStore.Intent
import com.qwwuyu.tkv.store.KVStore.State

internal interface KVStore : Store<Intent, State, Nothing> {
    sealed class Intent {
        data class DeleteItem(val key: String) : Intent()
        object AddClick : Intent()
        data class ItemClicked(val item: KVItem) : Intent()
        object Confirm : Intent()
        object Close : Intent()
        data class KeyTextChanged(val text: String) : Intent()
        data class ValueTextChanged(val text: String) : Intent()
    }

    data class State(
        val items: List<KVItem> = emptyList(),
        val addItem: AEItem = AEItem(false, "", ""),
        val editItem: AEItem = AEItem(false, "", ""),
    )
}

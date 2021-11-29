package com.qwwuyu.tkv

import com.arkivanov.decompose.value.Value

interface MKV {
    val models: Value<Model>

    fun finish()

    fun onItemClicked(item: KVItem)

    fun onAddItemClicked(key: String, value: String)

    fun onItemDeleteClicked(key: String)

    data class Model(
        val items: List<KVItem>
    )

    sealed class Output {
        object Finished : Output()
        data class Selected(val item: KVItem) : Output()
    }
}
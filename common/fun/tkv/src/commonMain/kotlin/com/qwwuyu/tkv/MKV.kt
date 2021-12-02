package com.qwwuyu.tkv

import com.arkivanov.decompose.value.Value

interface MKV {
    val models: Value<Model>

    fun finish()

    fun onItemDeleteClicked(key: String)

    fun onAddClicked()

    fun onItemClicked(item: KVItem)

    fun onConfirm()

    fun onClose()

    fun onKeyTextChanged(text: String)

    fun onValueTextChanged(text: String)

    data class Model(
        val items: List<KVItem>,
        val addItem: AEItem,
        val editItem: AEItem,
    )

    sealed class Output {
        object Finished : Output()
    }
}
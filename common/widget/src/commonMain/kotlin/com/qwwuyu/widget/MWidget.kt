package com.qwwuyu.widget

import com.arkivanov.decompose.value.Value

interface MWidget {
    val models: Value<Model>

    fun finish()

    fun onItemClicked(type: String)

    fun onTKV()

    data class Model(
        val type: String
    )

    sealed class Output {
        object Finished : Output()
        object TKV : Output()
        data class Selected(val type: String) : Output()
    }
}
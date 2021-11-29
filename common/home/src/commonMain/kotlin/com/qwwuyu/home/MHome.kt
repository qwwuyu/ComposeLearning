package com.qwwuyu.home

import com.arkivanov.decompose.value.Value

interface MHome {
    val models: Value<Model>

    fun onItemClicked(type: String)

    data class Model(
        val items: List<HomeItem>
    )

    sealed class Output {
        data class Selected(val type: String) : Output()
    }
}
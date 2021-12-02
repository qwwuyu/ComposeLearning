package com.qwwuyu.home

import com.arkivanov.decompose.value.Value

interface MHome {
    val models: Value<Model>

    fun onWidget(type: String)

    data class Model(
        val items: List<HomeItem>
    )

    sealed class Output {
        data class Widget(val type: String) : Output()
    }
}
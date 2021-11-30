package com.qwwuyu.home

import com.arkivanov.decompose.value.Value

interface MHome {
    val models: Value<Model>

    fun onWidget()

    data class Model(
        val items: List<HomeItem>
    )

    sealed class Output {
        object Widget : Output()
    }
}
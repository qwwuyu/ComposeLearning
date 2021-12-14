package com.qwwuyu.nested.home

import com.arkivanov.decompose.value.Value

interface MHome {
    val models: Value<Model>

    fun onClicked()

    fun onTextChanged(text: String)

    data class Model(
        val text: String,
    )
}
package com.qwwuyu.nested.me

import com.arkivanov.decompose.value.Value

interface MMe {
    val models: Value<Model>

    fun onClicked()

    fun onTextChanged(text: String)

    data class Model(
        val text: String,
    )
}
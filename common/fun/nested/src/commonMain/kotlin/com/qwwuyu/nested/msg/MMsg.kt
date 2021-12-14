package com.qwwuyu.nested.msg

import com.arkivanov.decompose.value.Value

interface MMsg {
    val models: Value<Model>

    fun onClicked()

    fun onTextChanged(text: String)

    data class Model(
        val text: String,
    )
}
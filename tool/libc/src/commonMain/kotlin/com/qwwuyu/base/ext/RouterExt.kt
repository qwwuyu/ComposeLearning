package com.qwwuyu.base.ext

import com.arkivanov.decompose.router.Router
import com.arkivanov.essenty.parcelable.Parcelable

interface TypeConfiguration<T> : Parcelable {
    val type: T
}

fun <T, C : TypeConfiguration<T>> Router<C, *>.frontOrPush(configuration: C) {
    navigate {
        val exist = it.firstOrNull { c -> c.type == configuration.type }
        if (exist == null) {
            it + configuration
        } else {
            it.filter { c -> c.type != configuration.type } + exist
        }
    }
}
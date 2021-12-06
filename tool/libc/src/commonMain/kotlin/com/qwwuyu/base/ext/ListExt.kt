package com.qwwuyu.base.ext

fun <T : Any, R : Any> Collection<T?>.listAllNon(block: (List<T>) -> R) {
    if (this.all { it != null }) {
        block(this.filterNotNull())
    }
}

fun <T : Any, R : Any> Collection<T?>.listAnyoneNon(block: (List<T>) -> R) {
    if (this.any { it != null }) {
        block(this.filterNotNull())
    }
}

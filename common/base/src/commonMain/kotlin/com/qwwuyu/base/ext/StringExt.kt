package com.qwwuyu.base.ext

inline fun String?.strBlackLet(block: (String) -> Unit) {
    if (this != null && this.isNotEmpty()) {
        block(this)
    }
}

fun String?.exist(): Boolean {
    return this != null && this.isNotEmpty()
}

fun String.toIntTry(): Int {
    return try {
        this.toInt()
    } catch (e: Exception) {
        0
    }
}
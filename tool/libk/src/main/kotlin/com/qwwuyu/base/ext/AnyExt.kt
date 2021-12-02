package com.qwwuyu.base.ext

inline fun <T1 : Any, T2 : Any, R : Any> anyNonLet(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}

inline fun <T1 : Any, T2 : Any, T3 : Any, R : Any> anyNonLet(p1: T1?, p2: T2?, p3: T3?, block: (T1, T2, T3) -> R?): R? {
    return if (p1 != null && p2 != null && p3 != null) block(p1, p2, p3) else null
}

inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, R : Any> anyNonLet(
    p1: T1?,
    p2: T2?,
    p3: T3?,
    p4: T4?,
    block: (T1, T2, T3, T4) -> R?
): R? {
    return if (p1 != null && p2 != null && p3 != null && p4 != null) block(p1, p2, p3, p4) else null
}

inline fun <T : Any> T?.anyNullReturn(block: () -> T): T = this ?: block()

fun <T> anyToNull(): T? = null

inline fun <T> T?.anyApplyEmpty(block: T.() -> Unit): T? {
    if (this != null) {
        block(this)
    }
    return null
}

inline fun <T : Any> T?.letIf(ifBlock: (T) -> Boolean, block: (T) -> Unit): T? {
    return if (this != null && ifBlock(this)) {
        block(this)
        this
    } else {
        null
    }
}

inline fun <T : Any> T?.letNon(block: (T) -> Unit): T? {
    if (this != null) {
        block(this)
    }
    return this
}

inline fun <T : Any> T?.letNull(block: () -> Unit): T? {
    if (this == null) {
        block()
    }
    return this
}
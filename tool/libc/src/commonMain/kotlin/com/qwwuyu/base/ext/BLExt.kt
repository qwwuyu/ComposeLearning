package com.qwwuyu.base.ext

inline fun <T> Boolean.ife(trueT: () -> T, falseT: () -> T): T {
    return if (this) trueT() else falseT()
}

inline fun <T> Boolean.t(trueT: () -> T): T? {
    return if (this) trueT() else null
}

inline fun <T> Boolean.f(falseT: () -> T): T? {
    return if (this) null else falseT()
}

fun <T> Boolean.tf(t1: T, t2: T): T {
    return if (this) t1 else t2
}

fun <T> Boolean?.tfn(t1: T, t2: T, t3: T): T {
    if (this == null) return t3
    return if (this) t1 else t2
}
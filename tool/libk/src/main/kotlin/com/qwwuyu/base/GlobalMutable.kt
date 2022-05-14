package com.qwwuyu.base

import kotlin.properties.Delegates

typealias ValueObserver<T> = (T) -> Unit

fun <T : Any?> globalMutable(initialValue: T): GlobalMutable<T> = GlobalMutable(initialValue)

class GlobalMutable<T : Any?>(initialValue: T) {
    private var observers = emptySet<ValueObserver<T>>()

    var value: T by Delegates.observable(initialValue) { _, _, value ->
        observers.forEach { it(value) }
    }

    fun subscribe(observer: ValueObserver<T>) {
        observers = observers + observer
        observer(value)
    }

    fun unsubscribe(observer: ValueObserver<T>) {
        observers = observers - observer
    }
}
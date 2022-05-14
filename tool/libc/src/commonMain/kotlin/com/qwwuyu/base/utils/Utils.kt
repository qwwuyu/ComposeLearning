package com.qwwuyu.base.utils

import androidx.compose.runtime.*
import com.qwwuyu.base.GlobalMutable
import com.qwwuyu.base.ValueObserver
import kotlinx.coroutines.CoroutineScope
import kotlin.jvm.JvmInline

@JvmInline
value class TProvidableCompositionLocal<T> constructor(
    private val delegate: ProvidableCompositionLocal<T?> = staticCompositionLocalOf { null }
) {
    val current: T @Composable get() = delegate.current!!
    infix fun provides(value: T) = delegate provides value
}

@Composable
inline fun <reified T : Any> EventBusReceive(noinline block: CoroutineScope.(T) -> Unit) {
    DisposableEffect(Unit, effect = {
        WLog.i("EventBusReceive:${T::class.simpleName}")
        val job = EventBus.receive<T> { block(it) }
        onDispose {
            WLog.i("EventBusCancel:${T::class.simpleName}")
            job.cancel()
        }
    })
}

@Composable
fun <T : Any?> GlobalMutable<T>.asState(): State<T> {
    val state = remember(this) { mutableStateOf(value) }

    DisposableEffect(this) {
        val observer: ValueObserver<T> = { state.value = it }

        subscribe(observer)

        onDispose {
            unsubscribe(observer)
        }
    }

    return state
}
package com.qwwuyu.base.platform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

@Composable
actual fun imageResource(res: String): Painter {
    val id = drawableId(res)
    return painterResource(id)
}

@Composable
actual fun vectorResource(res: String): Painter {
    val id = drawableId(res)
    return painterResource(id)
}

// TODO: improve resource loading
@Composable
private fun drawableId(res: String): Int {
    val imageName = res.substringAfterLast("/").substringBeforeLast(".")
    val drawableClass = rememberDR()()
    val field = drawableClass.getDeclaredField(imageName)
    val idValue = field.get(drawableClass) as Integer
    return idValue.toInt()
}

@Composable
fun rememberDR(): (() -> Class<*>) = LocalDR.current

val LocalDR = DRProvidableCompositionLocal()

@JvmInline
value class DRProvidableCompositionLocal internal constructor(
    private val delegate: ProvidableCompositionLocal<(() -> Class<*>)?> = staticCompositionLocalOf { null }
) {
    val current: (() -> Class<*>) @Composable get() = delegate.current!!
    infix fun provides(value: (() -> Class<*>)) = delegate provides value
}
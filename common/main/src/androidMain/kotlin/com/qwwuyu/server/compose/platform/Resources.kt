package com.qwwuyu.server.compose.platform

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.qwwuyu.server.compose.R

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
private fun drawableId(res: String): Int {
    val imageName = res.substringAfterLast("/").substringBeforeLast(".")
    val drawableClass = R.drawable::class.java
    val field = drawableClass.getDeclaredField(imageName)
    val idValue = field.get(drawableClass) as Integer
    return idValue.toInt()
}
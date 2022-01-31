package com.qwwuyu.imageloader

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.ContentScale

@Composable
actual fun CrossRemoteImage(
    url: String,
    modifier: Modifier,
    contentScale: ContentScale
) {
    val image = remember(url) { mutableStateOf<ImageBitmap?>(null) }
    LaunchedEffect(url) {
        ImageLoader.load(url)?.let {
            image.value = org.jetbrains.skia.Image.makeFromEncoded(it).toComposeImageBitmap()
        }
    }
    image.value?.let {
        Image(
            contentDescription = null,
            bitmap = it,
            modifier = modifier,
            contentScale = contentScale
        )
    }
}

@Composable
actual fun CrossByteArrayImage(
    byteArray: ByteArray,
    modifier: Modifier,
    contentScale: ContentScale,
) {
    Image(
        contentDescription = null,
        bitmap = org.jetbrains.skia.Image.makeFromEncoded(byteArray).toComposeImageBitmap(),
        modifier = modifier,
        contentScale = ContentScale.Crop,
    )
}
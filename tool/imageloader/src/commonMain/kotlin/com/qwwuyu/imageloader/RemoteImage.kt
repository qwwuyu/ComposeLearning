package com.qwwuyu.imageloader

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale

@Composable
fun RemoteImage(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit
) {
    CrossRemoteImage(
        url = url,
        modifier = modifier,
        contentScale = contentScale
    )
}

@Composable
fun ByteArrayImage(
    byteArray: ByteArray,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit
) {
    CrossByteArrayImage(
        byteArray = byteArray,
        modifier = modifier,
        contentScale = contentScale
    )
}
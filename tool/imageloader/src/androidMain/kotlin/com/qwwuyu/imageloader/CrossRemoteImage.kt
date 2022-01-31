package com.qwwuyu.imageloader

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter

@Composable
actual fun CrossRemoteImage(
    url: String,
    modifier: Modifier,
    contentScale: ContentScale,
) {
    Image(
        contentDescription = null,
        painter = rememberImagePainter(url),
        modifier = modifier,
        contentScale = ContentScale.Crop,
    )
}

@Composable
actual fun CrossByteArrayImage(
    byteArray: ByteArray,
    modifier: Modifier,
    contentScale: ContentScale,
) {
    Image(
        contentDescription = null,
        bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size).asImageBitmap(),
        modifier = modifier,
        contentScale = ContentScale.Crop,
    )
}
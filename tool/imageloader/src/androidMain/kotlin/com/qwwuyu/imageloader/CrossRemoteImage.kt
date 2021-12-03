package com.qwwuyu.imageloader

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
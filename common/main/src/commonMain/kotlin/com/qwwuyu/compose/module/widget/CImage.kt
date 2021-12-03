package com.qwwuyu.compose.module.widget

import androidx.compose.runtime.Composable
import com.qwwuyu.imageloader.RemoteImage

class CImage {
    companion object {
        @Composable
        fun main() {
            CImageMain()
        }
    }
}

@Composable
fun CImageMain() {
    RemoteImage("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png")
}
package com.qwwuyu.compose.module.widget

import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.qwwuyu.base.utils.WLog
import com.qwwuyu.compose.platform.httpApi
import com.qwwuyu.compose.platform.toJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CNet {
    companion object {
        @Composable
        fun main() {
            CNetMain()
        }
    }
}

@Composable
fun CNetMain() {
    var msg by remember { mutableStateOf("") }
    DisposableEffect(Unit) {
        val job = GlobalScope.launch(Dispatchers.Default) {
            msg = try {
                val get = httpApi.get()
                toJson(get)
            } catch (e: Exception) {
                e.message ?: ""
            }
            WLog.i(msg)
        }

        onDispose {
            job.cancel()
        }
    }
    Text(msg)
}
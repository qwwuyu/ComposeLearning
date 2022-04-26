package com.qwwuyu.compose.module.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qwwuyu.base.utils.EventBus
import com.qwwuyu.base.utils.WLog

class CEventBus {
    companion object {
        @Composable
        fun main() {
            CEventBusMain()
        }
    }
}

@Composable
fun CEventBusMain() {
    var click by remember { mutableStateOf(true) }
    Column(Modifier.verticalScroll(rememberScrollState())) {
        if (click) {
            register { click = false }
        } else {
            register2 { click = true }
        }

        Box(modifier = Modifier.fillMaxWidth().height(48.dp).clickable { EventBus.send("123") }) {
            Text(text = "sendStr123", modifier = Modifier.align(Alignment.CenterStart).padding(16.dp, 0.dp))
        }
        Box(modifier = Modifier.fillMaxWidth().height(48.dp).clickable { EventBus.send("124") }) {
            Text(text = "sendStr124", modifier = Modifier.align(Alignment.CenterStart).padding(16.dp, 0.dp))
        }
        Box(modifier = Modifier.fillMaxWidth().height(48.dp).clickable { EventBus.send(123) }) {
            Text(text = "sendInt123", modifier = Modifier.align(Alignment.CenterStart).padding(16.dp, 0.dp))
        }
    }
}

@Composable
fun register(click: () -> Unit) {
    DisposableEffect(Unit, effect = {
        WLog.i("DisposableEffect1")
        val job = EventBus.receive<String> {
            WLog.i("receive1:$it")
        }
        onDispose {
            WLog.i("onDispose1")
            job.cancel()
        }
    })
    Box(modifier = Modifier.fillMaxWidth().height(48.dp).clickable { click() }) {
        Text(text = "cancel", modifier = Modifier.align(Alignment.CenterStart).padding(16.dp, 0.dp))
    }
}

@Composable
fun register2(click: () -> Unit) {
    DisposableEffect(Unit, effect = {
        WLog.i("DisposableEffect2")
        val job = EventBus.receive<String> {
            WLog.i("receive2:$it")
        }
        onDispose {
            WLog.i("onDispose2")
            job.cancel()
        }
    })
    DisposableEffect(Unit, effect = {
        WLog.i("DisposableEffect3")
        val job = EventBus.receive<String> {
            WLog.i("receive3:$it")
        }
        onDispose {
            WLog.i("onDispose3")
            job.cancel()
        }
    })
    DisposableEffect(Unit, effect = {
        WLog.i("DisposableEffect4")
        val job = EventBus.receive<Int> {
            WLog.i("receive4:$it")
        }
        onDispose {
            WLog.i("onDispose4")
            job.cancel()
        }
    })
    Box(modifier = Modifier.fillMaxWidth().height(48.dp).clickable { click() }) {
        Text(text = "cancel", modifier = Modifier.align(Alignment.CenterStart).padding(16.dp, 0.dp))
    }
}
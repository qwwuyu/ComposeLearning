package com.qwwuyu.server.compose.module.test

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class CScaffold {
    companion object {
        val NAME = CScaffold::class.simpleName ?: ""

        @Composable
        fun main() {
            CScaffoldMain()
        }
    }
}

@Composable
fun CScaffoldMain() {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier,
        drawerContent = {
            Main()
        }
    ) {
        val scope = rememberCoroutineScope()
        Content(
            openDrawer = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }
        )
    }
}

@Composable
fun Main() {
    Text(
        "Main", modifier = Modifier.width(200.dp)
    )
}

@Composable
fun Content(openDrawer: () -> Unit) {
    Text("Content", Modifier.clickable { openDrawer() })
}
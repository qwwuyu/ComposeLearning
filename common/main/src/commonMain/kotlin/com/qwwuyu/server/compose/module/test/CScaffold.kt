package com.qwwuyu.server.compose.module.test

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

class CScaffold {
    companion object {
        @Composable
        fun main() {
            CScaffoldMain()
        }
    }
}

@Composable
fun CScaffoldMain() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier,
        drawerContent = {
            Text("click close", modifier = Modifier.fillMaxSize().clickable {
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            })
        }
    ) {
        Text("click open", Modifier.fillMaxSize().clickable {
            scope.launch {
                scaffoldState.drawerState.open()
            }
        })
    }
}
package com.qwwuyu.compose.module.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(50.dp),
                backgroundColor = Color(0xff6EC177),
                contentColor = Color.White,
                onClick = {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("onClick")
                    }
                },
                shape = MaterialTheme.shapes.medium.copy(CornerSize(percent = 50))
            ) {
                Icon(Icons.Default.Add, "添加")
            }
        },
        drawerContent = {
            Text("click close", modifier = Modifier.fillMaxSize().clickable {
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            })
        }
    ) {
        Column(Modifier.fillMaxSize()) {
            Text("showSnackbar", Modifier.clickable {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("123")
                }
            })
            Text("click open", Modifier.fillMaxSize().clickable {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            })
        }
    }
}
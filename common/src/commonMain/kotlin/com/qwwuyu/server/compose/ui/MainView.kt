package com.qwwuyu.server.compose.ui

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qwwuyu.server.compose.theme.WidgetGalleryTheme
import kotlinx.coroutines.launch

@Composable
fun MainView() {
    DisableSelection {
        WidgetGalleryTheme {
            val scaffoldState = rememberScaffoldState()
            Scaffold(
                scaffoldState = scaffoldState,
                modifier = Modifier,
                drawerContent = {
                    MainDrawer()
                }
            ) {
                val scope = rememberCoroutineScope()
                MainContent(
                    openDrawer = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun MainDrawer() {
    Text(
        "MainDrawer", modifier = Modifier.width(200.dp)
    )
}

@Composable
fun MainContent(openDrawer: () -> Unit) {
    Text("MainContent")
}
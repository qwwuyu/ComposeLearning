package com.qwwuyu.server

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.*
import com.qwwuyu.server.compose.module.test.TestCompose
import com.qwwuyu.server.compose.nav.LocalNav
import com.qwwuyu.server.compose.nav.Nav
import com.qwwuyu.server.compose.theme.AppTheme
import com.qwwuyu.server.compose.utils.WLog

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "qwwuyu",
        icon = painterResource("drawable-nodpi/icon.png"),
        state = WindowState(size = WindowSize(800.dp, 600.dp))
    ) {
        WLog.init(object : WLog.ILog {
            override fun i(contents: Any?) {
                println("qwlog:\t$contents")
            }
        })

        val navList = remember { mutableStateListOf<Nav.Path>(Nav.Path.Main) }
        val nav = object : Nav {
            override fun nav(path: Nav.Path) {
                WLog.i("nav path=$path")
                if (path is Nav.Path.Test) {
                    navList.add(path)
                }
            }
        }
        CompositionLocalProvider(LocalNav provides nav) {
            DisableSelection {
                AppTheme {
                    Column(Modifier.fillMaxSize()) {
                        TabView(navList)
                        Box(Modifier.weight(1f)) {
                            when (val path = navList.last()) {
                                is Nav.Path.Test -> TestCompose(path.name)
                                else -> TestCompose("")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TabView(navList: SnapshotStateList<Nav.Path>) {
    Row(Modifier.horizontalScroll(rememberScrollState())) {
        navList.forEachIndexed { index, path ->
            var modifier = Modifier.fillMaxWidth().height(48.dp)
            if (index != navList.size - 1) {
                modifier = modifier.clickable { navList.removeRange(index + 1, navList.size) }
            }
            Box(modifier = modifier) {
                Text(path.path, fontSize = 12.sp, modifier = Modifier.padding(12.dp, 0.dp).align(Alignment.Center))
            }
        }
    }
}


@Composable
private fun ApplicationScope.ApplicationTray(state: TrayState) {
    Tray(
        icon = painterResource("drawable-nodpi/icon.png"),
        state = TrayState(),
        tooltip = "hint qwwuyu",
        menu = { })
}
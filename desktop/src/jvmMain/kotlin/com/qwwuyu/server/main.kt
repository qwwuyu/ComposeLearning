package com.qwwuyu.server

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.*
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.badoo.reaktive.coroutinesinterop.asScheduler
import com.badoo.reaktive.scheduler.overrideSchedulers
import com.qwwuyu.database.DefaultBaseDatabase
import com.qwwuyu.router.integration.RouterComponent
import com.qwwuyu.compose.module.RouterContent
import com.qwwuyu.compose.theme.AppTheme
import kotlinx.coroutines.Dispatchers

fun main() {
    overrideSchedulers(main = Dispatchers.Main::asScheduler)

    val lifecycle = LifecycleRegistry()
    val rc = RouterComponent(
        componentContext = DefaultComponentContext(lifecycle = lifecycle),
        storeFactory = DefaultStoreFactory(),
        database = DefaultBaseDatabase()
    )

    application {
        val windowState = rememberWindowState()
        LifecycleController(lifecycle, windowState)

        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "qwwuyu",
            icon = painterResource("drawable-nodpi/icon.png"),
        ) {
            DisableSelection {
                AppTheme {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        RouterContent(rc)
                    }
                }
            }
        }
    }
}


@Composable
private fun ApplicationScope.ApplicationTray(state: TrayState) {
    Tray(
        icon = painterResource("drawable-nodpi/icon.png"),
        state = state,
        tooltip = "hint qwwuyu",
        menu = { })
}
package com.qwwuyu.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.badoo.reaktive.coroutinesinterop.asScheduler
import com.badoo.reaktive.scheduler.overrideSchedulers
import com.qwwuyu.base.handleErrorApplication
import com.qwwuyu.base.utils.WLog
import com.qwwuyu.compose.module.RouterContent
import com.qwwuyu.compose.theme.AppTheme
import com.qwwuyu.database.DefaultBaseDatabase
import com.qwwuyu.router.integration.RouterComponent
import kotlinx.coroutines.Dispatchers
import kotlin.system.exitProcess

fun main() {
    WLog.init(object : WLog.ILog {
        override fun i(contents: Any?) {
            println("$contents")
        }
    })
    handleErrorApplication {
        overrideSchedulers(main = Dispatchers.Main::asScheduler)
        val lifecycle = LifecycleRegistry()
        val rc = RouterComponent(
            componentContext = DefaultComponentContext(lifecycle = lifecycle),
            storeFactory = DefaultStoreFactory(),
            database = DefaultBaseDatabase()
        )

        val windowState = rememberWindowState()
        LifecycleController(lifecycle, windowState)

        Window(
            onCloseRequest = {
                exitApplication()
                exitProcess(0)
            },
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
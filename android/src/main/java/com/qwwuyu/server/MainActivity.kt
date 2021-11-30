package com.qwwuyu.server

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory
import com.qwwuyu.base.platform.LocalDR
import com.qwwuyu.compose.module.RouterContent
import com.qwwuyu.compose.theme.AppTheme
import com.qwwuyu.database.DefaultBaseDatabase
import com.qwwuyu.router.integration.RouterComponent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rc = RouterComponent(
            componentContext = defaultComponentContext(),
            storeFactory = LoggingStoreFactory(TimeTravelStoreFactory(DefaultStoreFactory())),
            database = DefaultBaseDatabase()
        )
        setContent {
            CompositionLocalProvider(LocalDR provides { R.drawable::class.java }) {
                AppTheme {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        RouterContent(rc)
                    }
                }
            }
        }
    }
}
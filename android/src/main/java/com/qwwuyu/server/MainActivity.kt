package com.qwwuyu.server

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory
import com.qwwuyu.database.DefaultBaseDatabase
import com.qwwuyu.router.integration.RouterComponent
import com.qwwuyu.server.compose.module.RouterContent
import com.qwwuyu.server.compose.theme.AppTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rc = RouterComponent(
            componentContext = defaultComponentContext(),
            storeFactory = LoggingStoreFactory(TimeTravelStoreFactory(DefaultStoreFactory())),
            database = DefaultBaseDatabase()
        )

        setContent {
            AppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    RouterContent(rc)
                }
            }
        }
    }
}
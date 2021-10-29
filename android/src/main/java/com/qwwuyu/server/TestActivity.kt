package com.qwwuyu.server

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.runtime.CompositionLocalProvider
import com.qwwuyu.server.compose.module.test.TestCompose
import com.qwwuyu.server.compose.nav.LocalNav
import com.qwwuyu.server.compose.nav.Nav
import com.qwwuyu.server.compose.theme.AppTheme
import com.qwwuyu.server.compose.utils.WLog

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = (intent?.getStringExtra("name")) ?: ""
        setContent {
            val nav = object : Nav {
                override fun nav(path: Nav.Path) {
                    WLog.i("TestActivity nav")
                }
            }
            CompositionLocalProvider(LocalNav provides nav) {
                DisableSelection {
                    AppTheme {
                        TestCompose(name)
                    }
                }
            }
        }
    }
}
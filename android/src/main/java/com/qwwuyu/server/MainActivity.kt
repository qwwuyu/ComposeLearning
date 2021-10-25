package com.qwwuyu.server

import android.content.Intent
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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val nav = object : Nav {
                override fun nav(path: String, name: String) {
                    WLog.i("MainActivity nav")
                    if (Nav.PATH_TEST == path) {
                        startActivity(Intent(this@MainActivity, TestActivity::class.java).putExtra("name", name))
                    }
                }
            }
            CompositionLocalProvider(LocalNav provides nav) {
                DisableSelection {
                    AppTheme {
                        TestCompose("")
                    }
                }
            }
        }
    }
}
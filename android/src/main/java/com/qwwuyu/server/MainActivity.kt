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
                override fun nav(path: Nav.Path) {
                    WLog.i("MainActivity nav")
                    if (path is Nav.Path.Test) {
                        startActivity(Intent(this@MainActivity, TestActivity::class.java).putExtra("name", path.name))
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
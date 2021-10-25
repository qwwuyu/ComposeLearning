package com.qwwuyu.server

import android.app.Application
import android.util.Log
import com.qwwuyu.server.compose.utils.WLog

class WApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        WLog.init(object : WLog.ILog {
            override fun i(contents: Any?) {
                Log.i("qwlog", "$contents")
            }
        })
    }
}
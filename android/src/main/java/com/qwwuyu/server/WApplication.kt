package com.qwwuyu.server

import android.util.Log
import com.qwwuyu.base.LibApplication
import com.qwwuyu.compose.utils.WLog

class WApplication : LibApplication() {
    override fun onCreate() {
        super.onCreate()
        WLog.init(object : WLog.ILog {
            override fun i(contents: Any?) {
                Log.i("qwlog", "$contents")
            }
        })
    }
}
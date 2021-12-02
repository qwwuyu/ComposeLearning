package com.qwwuyu.compose

import android.util.Log
import com.qwwuyu.base.LibApplication
import com.qwwuyu.base.handleError
import com.qwwuyu.base.utils.WLog

class WApplication : LibApplication() {
    override fun onCreate() {
        super.onCreate()
        handleError()
        WLog.init(object : WLog.ILog {
            override fun i(contents: Any?) {
                Log.i("qwlog", "$contents")
            }
        })
    }
}
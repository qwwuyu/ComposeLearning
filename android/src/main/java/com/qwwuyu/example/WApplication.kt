package com.qwwuyu.example

import com.qwwuyu.base.LibApplication
import com.qwwuyu.base.handleError
import com.qwwuyu.base.utils.WLog

class WApplication : LibApplication() {
    override fun onCreate() {
        super.onCreate()
        handleError()
        WLog.Builder().enable().enableLogHead(true).setLogTag("qwwuyu")
    }
}
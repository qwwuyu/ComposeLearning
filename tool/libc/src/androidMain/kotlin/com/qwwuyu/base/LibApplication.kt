package com.qwwuyu.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

open class LibApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        context = this
    }
}
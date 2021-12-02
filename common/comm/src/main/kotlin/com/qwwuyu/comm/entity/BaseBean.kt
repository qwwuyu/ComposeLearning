package com.qwwuyu.comm.entity

import kotlin.jvm.JvmField

class BaseBean<T> {
    @JvmField
    var state = 0

    @JvmField
    var info: String? = null

    @JvmField
    var data: T? = null
}
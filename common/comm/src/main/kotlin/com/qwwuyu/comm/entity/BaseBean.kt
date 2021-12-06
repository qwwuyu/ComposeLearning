package com.qwwuyu.comm.entity

data class BaseBean<T>(var state: Int = 0, var info: String? = null, var data: T? = null)
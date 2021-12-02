package com.qwwuyu.network

/**
 * 常量
 */
object HttpConfig {
    /* ======================== http params ======================== */
    /** 处理请求成功  */
    const val HTTP_SUC = 1

    const val HTTP_TOKEN_ERROR = 401

    /** 网络请求失败无状态码,有友好提示语  */
    const val HTTP_NO_CODE = Int.MIN_VALUE

    /** 网络请求成功,数据解析失败状态码  */
    const val HTTP_DATA_ERR = Int.MIN_VALUE + 1

    /** 网络请求失败无状态码,无处理提示语  */
    const val HTTP_NO_MSG = Int.MIN_VALUE + 2

    const val PAGE_NUM = 1

    const val PAGE_SIZE = 20

    /* ======================== http config ======================== */
    /** 登录  */
    const val LOGIN = "login"

    /** 基地址  */
    private const val ROOT_URL = "http://localhost:8080"

    /** 基础请求url  */
    var BASE_URL = "$ROOT_URL/"
}
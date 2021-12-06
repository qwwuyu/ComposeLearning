package com.qwwuyu.compose.platform

import com.qwwuyu.comm.entity.AccountBean
import com.qwwuyu.network.HttpHelper
import com.qwwuyu.base.gson.GsonHelper
import retrofit2.http.GET

@JvmSuppressWildcards
actual interface HttpApi {
    @GET("http://127.0.0.1:8089/i/test/get")
    actual suspend fun get(): AccountBean

    @GET("http://127.0.0.1:8080/test/timeout")
    actual suspend fun timeout(): AccountBean
}

actual val httpApi = HttpHelper.create(HttpApi::class.java)
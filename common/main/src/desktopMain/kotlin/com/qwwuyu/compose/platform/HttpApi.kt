package com.qwwuyu.compose.platform

import com.qwwuyu.comm.entity.AccountBean
import com.qwwuyu.network.HttpHelper
import retrofit2.http.GET

@JvmSuppressWildcards
@Deprecated("http should not be in compose")
actual interface HttpApi {
    @GET("/i/test/get")
    actual suspend fun get(): AccountBean
}

actual val httpApi = HttpHelper.create(HttpApi::class.java)
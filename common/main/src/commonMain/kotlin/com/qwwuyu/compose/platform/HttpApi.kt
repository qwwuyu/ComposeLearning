package com.qwwuyu.compose.platform

import com.qwwuyu.comm.entity.AccountBean

@Deprecated("http should not be in compose")
expect interface HttpApi {

    @Deprecated("http should not be in compose")
    suspend fun get(): AccountBean
}

expect val httpApi: HttpApi
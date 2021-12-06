package com.qwwuyu.compose.platform

import com.qwwuyu.comm.entity.AccountBean

expect interface HttpApi {
    suspend fun get(): AccountBean

    suspend fun timeout(): AccountBean
}

expect val httpApi: HttpApi
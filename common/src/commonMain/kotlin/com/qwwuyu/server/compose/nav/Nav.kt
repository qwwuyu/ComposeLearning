package com.qwwuyu.server.compose.nav

interface Nav {
    companion object {
        const val PATH_TEST = "test"
    }

    fun nav(path: String, name: String)
}
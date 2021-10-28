package com.qwwuyu.server.compose.nav

interface Nav {
    companion object {
        const val PATH_TEST = "test"
    }

    sealed class Path(val path: String) {
        object Main : Path("Main")
        data class Test(val name: String) : Path("Test")
    }

    fun nav(path: Path)
}
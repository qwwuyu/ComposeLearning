package com.qwwuyu.compose.utils

class WLog {
    companion object {
        private var log: ILog? = null

        fun init(log: ILog?) {
            this.log = log
        }

        fun i(contents: Any?) {
            log?.i(contents)
        }
    }

    interface ILog {
        fun i(contents: Any?)
    }
}
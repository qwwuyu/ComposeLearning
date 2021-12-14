package com.qwwuyu.base.utils

import java.io.File

fun File.checkCreate() {
    if (!this.exists()) this.createNewFile()
}

fun File.deleteCreate() {
    if (this.isFile) this.delete()
    this.createNewFile()
}
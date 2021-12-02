package com.qwwuyu.network.converter

import com.google.gson.JsonParseException

class DataException(var code: Int, var msg: String?) : JsonParseException(
    msg
)
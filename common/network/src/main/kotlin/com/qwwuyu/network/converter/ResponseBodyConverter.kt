/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qwwuyu.network.converter


import com.google.gson.Gson
import com.google.gson.internal.`$Gson$Types`
import com.qwwuyu.network.HttpConfig
import com.qwwuyu.base.gson.GsonHelper
import com.qwwuyu.base.gson.Ignore
import com.qwwuyu.comm.entity.BaseBean
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException
import java.lang.reflect.Type

internal class ResponseBodyConverter<T>(private val gson: Gson, private val type: Type) :
    Converter<ResponseBody, T?> {
    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T? {
        val jsonReader = gson.newJsonReader(value.charStream())
        return value.use {
            //Type resultType = TypeToken.getParameterized(BaseBean.class, $Gson$Types.canonicalize(this.type)).getType();
            val resultType: Type = `$Gson$Types`.newParameterizedTypeWithOwner(
                null, BaseBean::class.java, type
            )
            val response: BaseBean<T?> = gson.fromJson(jsonReader, resultType)
            val data = response.data
            if (HttpConfig.HTTP_SUC == response.state && response.data != null) {
                data
            } else if (HttpConfig.HTTP_SUC == response.state && GsonHelper.isTypeIgnore(type)) {
                Ignore.single as T
            } else if (HttpConfig.HTTP_SUC == response.state) {
                throw DataException(HttpConfig.HTTP_DATA_ERR, "未获取到数据")
            } else {
                throw DataException(response.state, response.info)
            }
        }
    }
}

package com.qwwuyu.network

import com.google.gson.JsonParseException
import com.qwwuyu.network.converter.ConverterFactory
import com.qwwuyu.network.converter.DataException
import com.qwwuyu.network.gson.GsonHelper
import kotlinx.coroutines.CancellationException
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import java.net.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLHandshakeException

/**
 * 处理请求相关辅助类
 */
object HttpHelper {
    private val okHttpClient: OkHttpClient
    private val retrofit: Retrofit

    init {
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)
        val builder = OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .cookieJar(JavaNetCookieJar(cookieManager))
        //HttpsFactory.safeHttps(builder)

        builder.addInterceptor(HttpLoggingInterceptor { message: String? -> }
            .setLevel(HttpLoggingInterceptor.Level.BODY))

        val client = builder.build()
        okHttpClient = client
        retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(HttpConfig.BASE_URL)
            .addConverterFactory(ConverterFactory.create(GsonHelper.getGson()))
            .build()
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

    /**
     * 默认的参数,不包括token
     */
    fun createMap(): MutableMap<String, Any> {
        val map: MutableMap<String, Any> = mutableMapOf()
        map["timestamp"] = System.currentTimeMillis()
        return map
    }

    fun handleError(e: Throwable, onErr: (code: Int, msg: String?) -> Unit) {
        when (e) {
            is CancellationException -> return
            is DataException -> onErr(e.code, e.msg)
            is JsonParseException -> onErr(HttpConfig.HTTP_NO_CODE, "数据解析失败")
            is SocketTimeoutException -> onErr(HttpConfig.HTTP_NO_CODE, "网络连接超时")
            is SocketException -> onErr(HttpConfig.HTTP_NO_CODE, "网络连接失败")
            is ConnectException -> onErr(HttpConfig.HTTP_NO_CODE, "网络连接失败")
            is SSLHandshakeException -> onErr(HttpConfig.HTTP_NO_CODE, "安全证书异常")
            is UnknownHostException, is NoRouteToHostException, is UnknownServiceException -> onErr(
                HttpConfig.HTTP_NO_CODE,
                "请求服务器失败"
            )
            is HttpException -> {
                when (e.code()) {
                    401 -> onErr(HttpConfig.HTTP_NO_MSG, null)
                    504 -> onErr(HttpConfig.HTTP_NO_CODE, "服务器连接超时")
                    404 -> onErr(HttpConfig.HTTP_NO_CODE, "请求的地址不存在")
                    else -> onErr(HttpConfig.HTTP_NO_CODE, "请求数据失败")
                }
            }
            else -> onErr(HttpConfig.HTTP_NO_MSG, "请求失败")
        }
    }
}
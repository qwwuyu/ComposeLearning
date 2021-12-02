package com.qwwuyu.network

import okhttp3.OkHttpClient
import java.io.InputStream
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import javax.net.ssl.*

object HttpsFactory {
    fun trustHttps(builder: OkHttpClient.Builder) {
        val trustManager: X509TrustManager = object : X509TrustManager {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return emptyArray()
            }
        }
        try {
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, arrayOf<TrustManager>(trustManager), SecureRandom())
            val socketFactory = sslContext.socketFactory
            builder.sslSocketFactory(socketFactory, trustManager)
                .hostnameVerifier { _: String?, _: SSLSession? -> true }
        } catch (ignored: Exception) {
        }
    }

    fun safeHttps(builder: OkHttpClient.Builder) {
        try {
            KeyStoreInputStream("qws.cer").use { input ->
                val certificateFactory = CertificateFactory.getInstance("X.509")
                val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
                keyStore.load(null)
                keyStore.setCertificateEntry("0", certificateFactory.generateCertificate(input))
                val sslContext = SSLContext.getInstance("TLS")
                val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
                trustManagerFactory.init(keyStore)
                sslContext.init(keyStore(), trustManagerFactory.trustManagers, SecureRandom())
                builder.sslSocketFactory(sslContext.socketFactory)
                    .hostnameVerifier { _: String?, _: SSLSession? -> true }
            }
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun keyStore(): Array<KeyManager> {
        return try {
            val keyStore = keyStore("qwc.bks", "123456")
            val keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
            keyManagerFactory.init(keyStore, "123456".toCharArray())
            keyManagerFactory.keyManagers
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun keyStore(fileName: String, keyStorePwd: String): KeyStore {
        try {
            KeyStoreInputStream(fileName).use { input ->
                val ks = KeyStore.getInstance(KeyStore.getDefaultType())
                ks.load(input, keyStorePwd.toCharArray())
                return ks
            }
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun KeyStoreInputStream(fileName: String): InputStream {
        TODO("Not yet implemented")
    }
}
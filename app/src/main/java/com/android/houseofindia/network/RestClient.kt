package com.android.houseofindia.network

import android.annotation.SuppressLint
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.security.GeneralSecurityException
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class RestClient {

    fun setupRestClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .client(SelfSigningClientBuilder().createClient())
            .build()
    }

    companion object {
        fun <T> provideAPI(retrofit: Retrofit, type: Class<T>) = retrofit.create(type)
    }

    class SelfSigningClientBuilder {

        private fun configureClient(client: OkHttpClient.Builder): OkHttpClient {
            val certs =
                arrayOf<TrustManager>(@SuppressLint("CustomX509TrustManager")
                object : X509TrustManager {
                    override fun getAcceptedIssuers(): Array<X509Certificate?> {
                        return arrayOfNulls(0)
                    }

                    @SuppressLint("TrustAllX509TrustManager")
                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(
                        chain: Array<X509Certificate>,
                        authType: String
                    ) {
                    }

                    @SuppressLint("TrustAllX509TrustManager")
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate>,
                        authType: String
                    ) {
                    }
                })
            var ctx: SSLContext? = null
            try {
                ctx = SSLContext.getInstance("TLS")
                ctx.init(null, certs, SecureRandom())
            } catch (ex: GeneralSecurityException) {
            }
            try {
                val hostnameVerifier = HostnameVerifier { _, _ -> true }
                client.hostnameVerifier(hostnameVerifier)
                client.sslSocketFactory(
                    ctx!!.socketFactory,
                    certs[0] as X509TrustManager
                )
            } catch (e: Exception) {
            }
            return client.build()
        }

        fun createClient(): OkHttpClient {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
            val client = OkHttpClient.Builder()
            client.apply {
                addInterceptor(httpLoggingInterceptor)
                connectTimeout(30, TimeUnit.SECONDS)
                readTimeout(30, TimeUnit.SECONDS)
                retryOnConnectionFailure(true)
            }
            return configureClient(client)
        }
    }
}
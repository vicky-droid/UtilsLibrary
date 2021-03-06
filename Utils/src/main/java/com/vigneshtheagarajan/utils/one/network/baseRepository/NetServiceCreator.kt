package com.vigneshtheagarajan.utils.one.network.baseRepository

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.vigneshtheagarajan.utils.one.network.HeaderAuthorizationInterceptor
import com.vigneshtheagarajan.utils.one.network.baseRepository.ApiServiceConstant.authToken
import com.vigneshtheagarajan.utils.one.network.chucker
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetServiceCreator {


    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)

    private  val CONNECT_TIME_OUT = 15L

    private  val READ_TIME_OUT = 20L

    private var baseUrl = ""

    private var debug = false

    var errorToast = false

    private var chucker = false

//    private var authToken = ""


    fun setBaseUrl(url: String): NetServiceCreator {
        this.baseUrl = url
        return this
    }
    fun setAuthToken(token: String): NetServiceCreator {
        authToken = token
        return this
    }

    fun enableDebug(bool: Boolean): NetServiceCreator {
        this.debug = bool
        return this
    }

    fun enableChucker(bool: Boolean): NetServiceCreator {
        this.chucker = bool
        return this
    }

    private val BODY by lazy(mode = LazyThreadSafetyMode.NONE) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val NONE by lazy(mode = LazyThreadSafetyMode.NONE) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }


    private val okHttpClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        val newClient = OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)         // 连接超时
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)               // 读取超时
            .addInterceptor(if (debug) BODY else NONE)      // 请求日志拦截器
            .addInterceptor(if (chucker) chucker() else NONE)
            .retryOnConnectionFailure(true)       // 失败重连

        if (authToken.isNotEmpty())
            newClient.addInterceptor(HeaderAuthorizationInterceptor())

        newClient.build()
//            .build()
    }

    private val retrofit by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        Retrofit.Builder()
            .baseUrl(baseUrl)
//            .addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }


}
package com.vigneshtheagarajan.utils.one.network.baseRepository

import com.vigneshtheagarajan.utils.one.network.HeaderAuthorizationInterceptor
import com.vigneshtheagarajan.utils.one.network.baseRepository.ApiServiceConstant.authToken
import com.vigneshtheagarajan.utils.one.network.chucker
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetServiceCreator {


    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    private  val CONNECT_TIME_OUT = 15L

    private  val READ_TIME_OUT = 20L

    private var baseUrl = ""

    private var debug = false

    var errorToast = false

    private var chucker = false

    private var authenticator = Authenticator.NONE

    fun setAuthenticator(authenticator: Authenticator): NetServiceCreator {
        this.authenticator = authenticator
        return this
    }


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
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(if (debug) BODY else NONE)
            .addInterceptor(if (chucker) chucker() else NONE)
            .retryOnConnectionFailure(true)
            .authenticator(authenticator)

        if (authToken.isNotEmpty())
            newClient.addInterceptor(HeaderAuthorizationInterceptor())

        newClient.build()
    }

    private val retrofit by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        Retrofit.Builder()
            .baseUrl(baseUrl)
//            .addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }


}
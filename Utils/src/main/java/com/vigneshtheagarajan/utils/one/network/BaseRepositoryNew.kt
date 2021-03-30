package com.vigneshtheagarajan.utils.one.network


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.vigneshtheagarajan.utils.one.app.UtilsLib.context
import com.vigneshtheagarajan.utils.one.log
import com.vigneshtheagarajan.utils.one.network.baseRepository.ApiServiceConstant.authToken
import com.vigneshtheagarajan.utils.one.view.hideNewLoader
import kotlinx.coroutines.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


//object NetServiceCreator {
//
//    private const val CONNECT_TIME_OUT = 15L
//
//    private const val READ_TIME_OUT = 20L
//
//   private  var baseUrl  = ""
//
//    var debug  = false
//
//    var errorToast = false
//
//    var chucker = false
//
//    var authToken = ""
//
//    fun settings(debug:String?=null,baseUrl:String?=null){
//
//    }
//
//    fun SetBaseUrl(url:String){
//
//
//    }
//
//    private val BODY by lazy(mode = LazyThreadSafetyMode.NONE) {
//        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//    }
//
//    private val NONE by lazy(mode = LazyThreadSafetyMode.NONE) {
//        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
//    }
//
//
//    private val okHttpClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
//      val tt=  OkHttpClient.Builder()
//            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)         // 连接超时
//            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)               // 读取超时
//            .addInterceptor(if (debug) BODY else NONE)      // 请求日志拦截器
//            .addInterceptor(if (chucker) chucker() else NONE)
//            .retryOnConnectionFailure(true)       // 失败重连
//
//        if (authToken.isNotEmpty())
//            tt.addInterceptor(HeaderAuthorizationInterceptor())
//
//        tt.build()
////            .build()
//    }
//
//
//
//
//
//    private val retrofit by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
//        Retrofit.Builder()
//            .baseUrl(baseUrl)
////            .addConverterFactory(MoshiConverterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
//            .client(okHttpClient)
//            .build()
//    }
//
//
//
//    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
//
//
//    inline fun <reified T> create(): T = create(T::class.java)
//}



fun chucker(): ChuckerInterceptor {
    // Create the Collector
    val chuckerCollector = ChuckerCollector(
        context = context!!,
        // Toggles visibility of the push notification
        showNotification = true,
        // Allows to customize the retention period of collected data
        retentionPeriod = RetentionManager.Period.FOREVER
    )

// Create the Interceptor
    val chuckerInterceptor = ChuckerInterceptor.Builder(context!!)
        // The previously created Collector
        .collector(chuckerCollector)
        // The max body content length in bytes, after this responses will be truncated.
        .maxContentLength(250_000L)
        // List of headers to replace with ** in the Chucker UI
        .redactHeaders("Auth-Token", "Bearer")
        // Read the whole response body even when the client does not consume the response completely.
        // This is useful in case of parsing errors or when the response body
        // is closed before being read like in Retrofit with Void and Unit types.
        .alwaysReadResponseBody(true)
        // Use decoder when processing request and response bodies. When multiple decoders are installed they
        // are applied in an order they were added.
//        .addBodyDecoder(decoder)
        .build()

    return chuckerInterceptor
}

//fun headerAuthorizationInterceptor(): Interceptor {
//    return object : Interceptor {
//        @Throws(IOException::class)
//        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
//            var request = chain.request()
//            val headers = request.headers.newBuilder()
//                .add("Authorization", authToken)
//                .add("Content-Type", "application/json").build()
//            request = request.newBuilder().headers(headers).build()
//            return chain.proceed(request)
//        }
//    }
//
//
//}

class HeaderAuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        val headers = request.headers.newBuilder()
            .add("Authorization", authToken)
            .add("Content-Type", "application/json").build()
        request = request.newBuilder().headers(headers).build()
        return chain.proceed(request)    }


}












abstract class BaseRepositoryNew5<T>(@PublishedApi internal val service: T) {

//abstract fun loadData(data: Any? = null): LiveData<T>?


inline fun <C>fetchData(crossinline call: (T) -> Deferred<Response<C>>): LiveData<C> {
    val result = MutableLiveData<C>()

    CoroutineScope(Dispatchers.IO).launch {
        val request = call(service)
        withContext(Dispatchers.Main) {
            try {
                val response = request.await()
                if (response.isSuccessful) {
                    log("response: ${response.raw()}")
                    result.value = response.body()
                } else {
                    log("Error occurred with code ${response.raw()}")
                    log("Error occurred with code ${response.message()}")
                    log("Error occurred with code ${response.errorBody()}")
//                    if (errorToast)
//                    toast("Error occurred with code ${response.message()}")
//                        result.value =  null
                }
                hideNewLoader()
            } catch (e: HttpException) {
                hideNewLoader()
                log("Error HttpException: ${e.message()}")
                log("Error HttpException: ${e.message}")
            } catch (e: Throwable) {
                hideNewLoader()
                log("Error Throwable: ${e.message}")
                log("Error Throwable: ${e.stackTrace}")
            } catch (e: Exception) {
                hideNewLoader()
                log("Error Exception: ${e.message}")
                log("Error Exception: ${e.stackTrace}")
            }
        }
    }

    return result
}
}





class NetServiceCreator {


    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)


    private  val CONNECT_TIME_OUT = 15L

    private  val READ_TIME_OUT = 20L

    private var baseUrl = ""

   private var debug = false

    var errorToast = false

   private var chucker = false

    private var authToken = ""


    fun setBaseUrl(url: String): NetServiceCreator {
        this.baseUrl = url
        return this
    }
    fun setauthToken(token: String): NetServiceCreator {
        this.authToken = token
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
        val tt = OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)         // 连接超时
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)               // 读取超时
            .addInterceptor(if (debug) BODY else NONE)      // 请求日志拦截器
            .addInterceptor(if (chucker) chucker() else NONE)
            .retryOnConnectionFailure(true)       // 失败重连

        if (authToken.isNotEmpty())
            tt.addInterceptor(HeaderAuthorizationInterceptor())

        tt.build()
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


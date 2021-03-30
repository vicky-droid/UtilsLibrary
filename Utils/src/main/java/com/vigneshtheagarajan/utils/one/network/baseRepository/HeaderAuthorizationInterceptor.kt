package com.vigneshtheagarajan.utils.one.network.baseRepository

import com.vigneshtheagarajan.utils.one.network.baseRepository.ApiServiceConstant.authToken
import okhttp3.Interceptor

class HeaderAuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        val headers = request.headers.newBuilder()
            .add("Authorization", authToken)
            .add("Content-Type", "application/json").build()
        request = request.newBuilder().headers(headers).build()
        return chain.proceed(request)    }


}

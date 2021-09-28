package com.vigneshtheagarajan.utilslibrary.app.network

import com.vigneshtheagarajan.utils.one.network.baseRepository.NetServiceCreator

object NetRequest {
    val authToken="" //optional auth token

    val commonService by lazy(mode = LazyThreadSafetyMode.NONE) {
        NetServiceCreator()
            .setBaseUrl("https://api.github.com/users/") // set Base Url
            .setAuthToken(authToken)             //set authToken
            .enableDebug(false)            //enable disable HttpLoggingInterceptor
            .enableChucker(true)           //enable disable chucker debug library
            .setPrettyJson(true)                //beautifully print json as Human readable in Logcat
            .create(ApiCommonService::class.java)

    }

}
package com.vigneshtheagarajan.utils.one.network.baseRepository

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.vigneshtheagarajan.utils.one.app.UtilsLib

fun chucker(): ChuckerInterceptor {
    // Create the Collector
    val chuckerCollector = ChuckerCollector(
        context = UtilsLib.context!!,
        // Toggles visibility of the push notification
        showNotification = true,
        // Allows to customize the retention period of collected data
        retentionPeriod = RetentionManager.Period.FOREVER
    )

// Create the Interceptor
    val chuckerInterceptor = ChuckerInterceptor.Builder(UtilsLib.context!!)
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

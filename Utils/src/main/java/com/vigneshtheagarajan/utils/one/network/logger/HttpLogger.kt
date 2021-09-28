package com.vigneshtheagarajan.utils.one.network.logger

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor


class HttpLogger : HttpLoggingInterceptor.Logger {

    override fun log(message: String) {
        Log.d(Tag,message.getPrettyJsonString())

    }
    companion object{
        var Tag = "HttpLogger"
    }
}




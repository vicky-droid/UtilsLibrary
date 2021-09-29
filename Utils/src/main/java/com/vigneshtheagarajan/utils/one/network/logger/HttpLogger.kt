package com.vigneshtheagarajan.utils.one.network.logger

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor


class HttpLogger : HttpLoggingInterceptor.Logger {

    override fun log(message: String) {
        logFull(Log.DEBUG,Tag,message.getPrettyJsonString())
    }
    companion object{
        var Tag = "HttpLogger"
    }

    fun logFull(priority: Int, tag: String?, message: String) {
        val MAX_LOG_LENGTH = 4000

        if (message.length < MAX_LOG_LENGTH) {
            if (priority == Log.ASSERT) {
                Log.wtf(tag, message)
            } else {
                Log.println(priority, tag, message)
            }
            return
        }

        var i = 0
        val length = message.length
        while (i < length) {
            var newline = message.indexOf('\n', i)
            newline = if (newline != -1) newline else length
            do {
                val end = Math.min(newline, i + MAX_LOG_LENGTH)
                val part = message.substring(i, end)
                if (priority == Log.ASSERT) {
                    Log.wtf(tag, part)
                } else {
                    Log.println(priority, tag, part)
                }
                i = end
            } while (i < newline)
            i++
        }
    }
}




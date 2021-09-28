package com.vigneshtheagarajan.utils.one.network.logger

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class HttpLogger : HttpLoggingInterceptor.Logger {

    override fun log(message: String) {
        val j =  getJsonString(message)
        Log.d(Tag,j)

    }
    companion object{
        var Tag = "HttpLogger"
    }


    private fun getJsonString(msg: String): String {
        val gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()
        val message: String = try {
            when {
                msg.startsWith("{") -> {
                    val jsonObject = JSONObject(msg)
                    gson.toJson(jsonObject)
                }
                msg.startsWith("[") -> {
                    val jsonArray = JSONArray(msg)
                    gson.toJson(jsonArray)
                }
                else -> {
                    msg
                }
            }
        } catch (e: JSONException) {
            msg
        } catch (e1: OutOfMemoryError) {
            e1.toString()
        }
        return message
    }

}
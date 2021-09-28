package com.vigneshtheagarajan.utils.one.network.logger

import com.google.gson.GsonBuilder
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


 internal fun String.getPrettyJsonString(): String {
    val gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()
    val message: String = try {
        when {
            this.startsWith("{") -> {
                val jsonObject = JSONObject(this)
                gson.toJson(jsonObject)
            }
            this.startsWith("[") -> {
                val jsonArray = JSONArray(this)
                gson.toJson(jsonArray)
            }
            else -> {
                this
            }
        }
    } catch (e: JSONException) {
        this
    } catch (e1: OutOfMemoryError) {
        e1.toString()
    }
    return message
}
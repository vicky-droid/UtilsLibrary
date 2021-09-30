package com.vigneshtheagarajan.utils.one.network.logger

import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.json.JSONException


internal fun String.getPrettyJsonString(): String {
    val gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()
    val message: String = try {
        when {
            this.startsWith("{") -> {
                val jsonObject= gson.fromJson(this, JsonObject::class.java)
                gson.toJson(jsonObject)
            }
            this.startsWith("[") -> {
                val jsonArray= gson.fromJson(this, JsonArray::class.java)
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
package com.vigneshtheagarajan.utilslibrary.app.network

import com.google.gson.JsonObject
import retrofit2.http.GET

interface ApiCommonService {
    @GET("vicky-droid")
    suspend fun getGithubRepos():  JsonObject
}
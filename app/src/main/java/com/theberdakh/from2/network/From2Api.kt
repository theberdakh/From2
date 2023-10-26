package com.theberdakh.from2.network

import com.theberdakh.from2.data.remote.ResponseData
import com.theberdakh.from2.data.remote.TranslateRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface From2Api {
    @POST("/api/v1/translate")
    suspend fun translate(@Body translateRequest: TranslateRequest): Response<ResponseData>

    @POST("/api/v1/transliterate")
    suspend fun transliterate(@Body translateRequest: TranslateRequest): Response<ResponseData>
}
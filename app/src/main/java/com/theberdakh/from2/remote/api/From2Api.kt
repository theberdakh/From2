package com.theberdakh.from2.remote.api

import com.theberdakh.from2.remote.translate.response.TranslateResponse
import com.theberdakh.from2.remote.translate.request.TranslateRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface From2Api {
    @POST("/api/v1/translate")
    suspend fun translate(@Body translateRequest: TranslateRequest): Response<TranslateResponse>

    @POST("/api/v1/transliterate")
    suspend fun transliterate(@Body translateRequest: TranslateRequest): Response<TranslateResponse>
}
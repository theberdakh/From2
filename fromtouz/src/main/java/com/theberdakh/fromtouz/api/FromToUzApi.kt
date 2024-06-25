package com.theberdakh.fromtouz.api

import com.theberdakh.fromtouz.translate.request.TranslateRequest
import com.theberdakh.fromtouz.translate.response.TranslateResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

internal interface FromToUzApi {

    @POST("/api/v1/translate")
    suspend fun translate(@Body translateRequest: TranslateRequest): Response<TranslateResponse>

}
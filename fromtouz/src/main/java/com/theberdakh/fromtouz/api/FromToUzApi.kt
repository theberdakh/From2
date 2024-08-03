package com.theberdakh.fromtouz.api

import com.theberdakh.fromtouz.translate.request.TranslateRequest
import com.theberdakh.fromtouz.transliterate.request.TransliterateBody
import com.theberdakh.fromtouz.transliterate.request.TransliterateRequest
import com.theberdakh.fromtouz.transliterate.response.TransliterateResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

internal interface FromToUzApi {

    @POST("/api/v1/translate")
    suspend fun translate(@Body translateRequest: TranslateRequest): Response<TransliterateResponse>

    @POST("/api/v1/transliterate")
    suspend fun transliterate(@Body transliterateRequest: TransliterateRequest): Response<TransliterateResponse>

}
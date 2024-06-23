package com.theberdakh.fromtouz

import com.theberdakh.fromtouz.api.FromToUzApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FromToUzApiClient {
    private const val BASE_URL = "https://api.from-to.uz"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val fromToUzApi: FromToUzApi = retrofit.create(FromToUzApi::class.java)
}
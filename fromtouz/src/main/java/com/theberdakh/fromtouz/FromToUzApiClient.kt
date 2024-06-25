package com.theberdakh.fromtouz

import com.theberdakh.fromtouz.api.FromToUzApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object FromToUzApiClient {
    private const val BASE_URL = "https://api.from-to.uz"

    private val httpsLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BODY
    )

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpsLoggingInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val fromToUzApi: FromToUzApi = retrofit.create(FromToUzApi::class.java)
}
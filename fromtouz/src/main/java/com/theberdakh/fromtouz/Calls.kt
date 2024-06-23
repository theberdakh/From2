package com.theberdakh.fromtouz

import com.theberdakh.fromtouz.translate.request.TranslateBody
import com.theberdakh.fromtouz.translate.request.TranslateRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

suspend fun translate(langFrom: String, langTo: String, text: String) = flow {
    val translateBody = TranslateBody(langFrom = langFrom, langTo = langTo, text = text)
    val translateRequest = TranslateRequest(translateBody)

        val response = FromToUzApiClient.fromToUzApi.translate(translateRequest)
        if (response.isSuccessful){
            if (response.body() != null){
                emit(ResultData.Success(response.body()))
            } else {
                emit(ResultData.Message("response is null"))
            }
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))


}.flowOn(Dispatchers.IO)
package com.theberdakh.fromtouz

import android.util.Log
import com.theberdakh.fromtouz.translate.TranslateLanguage
import com.theberdakh.fromtouz.translate.request.TranslateBody
import com.theberdakh.fromtouz.translate.request.TranslateRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

suspend fun translate(
    langFrom: TranslateLanguage, langTo: TranslateLanguage, text: String,
    onSuccess: (String) -> Unit, onMessage: (String) -> Unit, onError: (Throwable) -> Unit
) {
    val response = translateRequest(langFrom.code, langTo.code, text)
    response.collectLatest {
        when (it) {
            is ResultData.Success -> onSuccess.invoke(it.data.result)
            is ResultData.Message -> onMessage.invoke(it.message)
            is ResultData.Error -> onError.invoke(it.error)
        }
    }
}

suspend fun translateRequest(langFrom: String, langTo: String, text: String) = flow {
    val translateBody = TranslateBody(langFrom = langFrom, langTo = langTo, text = text)
    val translateRequest = TranslateRequest(translateBody)

    val response = FromToUzApiClient.fromToUzApi.translate(translateRequest)
    if (response.isSuccessful) {
        if (response.body() != null) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message("response is null"))
        }
    } else {
        emit(ResultData.Message(response.message()))
    }

}.catch {
    emit(ResultData.Error(it))
}.flowOn(Dispatchers.IO)
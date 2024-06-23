package com.theberdakh.fromtouz

import android.util.Log
import com.theberdakh.fromtouz.translate.request.TranslateBody
import com.theberdakh.fromtouz.translate.request.TranslateRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach

suspend fun translateState(
    langFrom: String, langTo: String, text: String,
    onSuccess: (String) -> Unit, onMessage: (String) -> Unit, onError: (Throwable) -> Unit
) {
    val response = translate(langFrom, langTo, text)
    response.collectLatest {
        when (it) {
            is ResultData.Success -> onSuccess.invoke(it.data.result)
            is ResultData.Message -> onMessage.invoke(it.message)
            is ResultData.Error -> onError.invoke(it.error)
        }
    }
}

suspend fun translate(langFrom: String, langTo: String, text: String) = flow {
    val translateBody = TranslateBody(langFrom = langFrom, langTo = langTo, text = text)
    val translateRequest = TranslateRequest(translateBody)

    val response = FromToUzApiClient.fromToUzApi.translate(translateRequest)
    if (response.isSuccessful) {
        Log.i("Response", "isSuccessful: ${response.body()}")
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
package com.theberdakh.fromtouz

import com.theberdakh.fromtouz.translate.TranslateLanguage
import com.theberdakh.fromtouz.translate.request.TranslateBody
import com.theberdakh.fromtouz.translate.request.TranslateRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun getAllTranslateLanguages() = TranslateLanguage.entries.toTypedArray()


suspend fun translate(
    langFrom: TranslateLanguage, langTo: TranslateLanguage, text: String,
    onSuccess: (String) -> Unit, onMessage: (String) -> Unit, onError: (Throwable) -> Unit
) {
    val response = translateRequest(langFrom.code, langTo.code, text)

    response.collectLatest {
        when (it) {
            is TranslationResult.Success -> onSuccess.invoke(it.data.result)
            is TranslationResult.Message -> onMessage.invoke(it.message)
            is TranslationResult.Error -> onError.invoke(it.error)
        }
    }
}

fun translateRequest(langFrom: String, langTo: String, text: String) = flow {
    val translateBody = TranslateBody(langFrom = langFrom, langTo = langTo, text = text)
    val translateRequest = TranslateRequest(translateBody)

    val response = FromToUzApiClient.fromToUzApi.translate(translateRequest)
    if (response.isSuccessful) {
        if (response.body() != null) {
            emit(TranslationResult.Success(response.body()!!))
        }  else {
            emit(TranslationResult.Message(response.message()))
        }
    }
}.catch {
    emit(TranslationResult.Error(it))
}.flowOn(Dispatchers.IO)
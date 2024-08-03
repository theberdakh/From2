package com.theberdakh.fromtouz

import com.theberdakh.fromtouz.translate.TranslateLanguage
import com.theberdakh.fromtouz.translate.request.TranslateBody
import com.theberdakh.fromtouz.translate.request.TranslateRequest
import com.theberdakh.fromtouz.transliterate.TransliterateLanguage
import com.theberdakh.fromtouz.transliterate.request.TransliterateBody
import com.theberdakh.fromtouz.transliterate.request.TransliterateRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun getAllTranslateLanguages() = TranslateLanguage.entries.toTypedArray()
fun getAllTransliterateLanguages() = TransliterateLanguage.entries.toTypedArray()

suspend fun translate(
    langFrom: TranslateLanguage, langTo: TranslateLanguage, text: String,
    onSuccess: (String) -> Unit, onMessage: (String) -> Unit, onError: (Throwable) -> Unit
) {
    val response = translateRequest(langFrom.code, langTo.code, text)

    response.collectLatest {
        when (it) {
            is TranslationResult.Success -> {
                /*Manual handling bug of server that returns Uppercased words even if it starts from lower case*/
                if (text.isNotEmpty() && text.first().isLowerCase()) {
                    onSuccess.invoke(it.data.result.replaceFirstChar { firstChar -> firstChar.lowercase() })
                } else {
                    onSuccess.invoke(it.data.result)
                }
            }
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

fun transliterateRequest(langFrom: String, langTo: String, text: String) = flow {
    val translateBody = TransliterateBody(langFrom = langFrom, langTo = langTo, text = text)
    val translateRequest = TransliterateRequest(translateBody)

    val response = FromToUzApiClient.fromToUzApi.transliterate(translateRequest)
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

suspend fun transliterate(
    langFrom: TransliterateLanguage, langTo: TransliterateLanguage, text: String,
    onSuccess: (String) -> Unit, onMessage: (String) -> Unit, onError: (Throwable) -> Unit
) {
    val response = transliterateRequest(langFrom.code, langTo.code, text)

    response.collectLatest {
        when (it) {
            is TranslationResult.Success -> {
                /*Manual handling bug of server that returns Uppercased words even if it starts from lower case*/
                if (text.isNotEmpty() && text.first().isLowerCase()) {
                    onSuccess.invoke(it.data.result.replaceFirstChar { firstChar -> firstChar.lowercase() })
                } else {
                    onSuccess.invoke(it.data.result)
                }
            }
            is TranslationResult.Message -> onMessage.invoke(it.message)
            is TranslationResult.Error -> onError.invoke(it.error)
        }
    }
}
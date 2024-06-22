package com.theberdakh.from2.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theberdakh.from2.domain.TranslateRepository
import com.theberdakh.from2.remote.ResultData
import com.theberdakh.from2.remote.translate.request.TranslateBody
import com.theberdakh.from2.remote.translate.request.TranslateRequest
import com.theberdakh.from2.remote.translate.response.TranslateResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class TranslateViewModel(private val translateRepository: TranslateRepository) : ViewModel() {

    val translateResponseSuccess = MutableSharedFlow<TranslateResponse>()
    val translateResponseMessage = MutableSharedFlow<String>()
    val translateResponseError = MutableSharedFlow<Throwable>()

    suspend fun translate(langFrom: String, langTo: String, text: String) {
        val translateRequest = TranslateRequest(
            TranslateBody(
                lang_from = langFrom,
                lang_to = langTo,
                text = text
            )
        )

        val translateResponse = translateRepository.translate(translateRequest)
        translateResponse.onEach {
            when(it) {
                is ResultData.Success -> {
                    translateResponseSuccess.emit(it.data!!)
                }
                is ResultData.Message -> {
                    translateResponseMessage.emit(it.message)
                }
                is ResultData.Error -> {
                    translateResponseError.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)

    }
}
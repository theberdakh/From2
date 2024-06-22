package com.theberdakh.from2.domain

import com.theberdakh.from2.remote.ResultData
import com.theberdakh.from2.remote.api.From2Api
import com.theberdakh.from2.remote.translate.request.TranslateRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TranslateRepository(private val from2Api: From2Api) {

    suspend fun translate(translateRequest: TranslateRequest) = flow {
        val response = from2Api.translate(translateRequest)
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


}
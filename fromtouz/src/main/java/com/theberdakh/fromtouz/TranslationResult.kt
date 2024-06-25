package com.theberdakh.fromtouz

sealed interface TranslationResult<out T> {
    data class Success<T>(val data: T): TranslationResult<T>
    data class Message<T>(val message: String): TranslationResult<T>
    data class Error<T>(val error: Throwable): TranslationResult<T>
}

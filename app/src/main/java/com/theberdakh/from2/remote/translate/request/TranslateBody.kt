package com.theberdakh.from2.remote.translate.request

data class TranslateBody(
    val lang_from: String,
    val lang_to: String,
    val text: String
)
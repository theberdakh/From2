package com.theberdakh.fromtouz.transliterate.request

import com.google.gson.annotations.SerializedName

data class TransliterateBody(
    @SerializedName("lang_from")
    val langFrom: String,
    @SerializedName("lang_to")
    val langTo: String,
    val text: String
)

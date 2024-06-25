package com.theberdakh.fromtouz.translate.request

import com.google.gson.annotations.SerializedName

internal data class TranslateBody(
    @SerializedName("lang_from")
    val langFrom: String,
    @SerializedName("lang_to")
    val langTo: String,
    val text: String
)

package com.theberdakh.from2

import com.theberdakh.from2.data.Languages

fun Languages.convert(): String {
    return when(this){
        Languages.CYRILLIC -> "uz_cyrillic"
        Languages.LATIN -> "uz_latin"
        Languages.UZBEK -> "uz"
        Languages.KARAKALPAK -> "kaa"
        Languages.UNDEFINED -> "Undefined language"
        Languages.CYRILLIC_LATIN -> "kaa_latin"
        Languages.CYRILLIC_KARAKALPAK ->"kaa_cyrillic"
    }
}


fun String.convert(): Languages {
    return when(this){
        "Kirill" -> Languages.CYRILLIC
        "Latın" -> Languages.LATIN
        "Uzbek" -> Languages.UZBEK
        "Karakalpak" -> Languages.KARAKALPAK
        else -> Languages.UNDEFINED
    }
}
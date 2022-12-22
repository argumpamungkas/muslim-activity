package com.argumelar.muslimactivity.model

import java.io.Serializable

data class QuranResponse(
    val message: String?,
    val data: List<QuranModel>?
) : Serializable

data class QuranModel(
    val number: Int?,
    val ayahCount: Int?,
    val asma: NameSurah?,
    val recitation: RecitationFull?
) : Serializable

data class NameSurah(
    val ar: ArabShort?,
    val id: IndoShort?, // nama surat
    val translation: SurahTranslation?
) : Serializable

data class RecitationFull(
    val full: String?
) : Serializable

data class ArabShort(
    val short: String?,
    val long: String?,
) : Serializable

data class IndoShort(
    val short: String?,
    val long: String?,
) : Serializable

data class SurahTranslation(
    val en: String?,
    val id: String?
) : Serializable
package com.argumelar.muslimactivity.model

data class DetailQuranResponse (
    val message : String?,
    val data: DataQuran?
)

data class DataQuran(
    val number: Int?,
    val ayahCount: Int?,
    val asma: NamaSurat?,
    val preBismillah: PreBismillah?,
    val type: TypeSurat?,
    val tafsir: Tafsir?,
    val recitation: Recitation?,
    val ayahs: ArrayList<DetailAyahs>?
)

//NAMA SURAT
data class NamaSurat(
    val id: IdShort?, // nama surat id
    val translation: SuratTranslation?
)

data class IdShort(
    val short: String?,
    val long: String?,
)

data class SuratTranslation(
    val en: String?,
    val id: String?
)

//PRE BISMILLAH
data class PreBismillah(
    val text: TextBismillah?,
    val translation: TranslateBismillah?
)

data class TextBismillah(
    val ar: String?,
    val read: String?
)

data class TranslateBismillah(
    val id: String?
)

// TYPE SURAT
data class TypeSurat(
    val ar : String?,
    val id : String?
)

// TAFSIR
data class Tafsir(
    val id: String?
)

// RECITATION
data class Recitation(
    val full: String?
)

// Detail Ayat
data class DetailAyahs(
    val number: NumberAyah?,
    val text: TextAyah?,
    val translation: TranslationAyah?
)

data class NumberAyah(
    val inquran: Int?,
    val insurah: Int?,
)

data class TextAyah(
    val ar: String?,
    val read: String?
)

data class TranslationAyah(
    val en: String?,
    val id: String?
)



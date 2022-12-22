package com.argumelar.muslimactivity.model

data class JadwalSholat(
    val status: String?,
    val query: Format?,
    val jadwal: Jadwal?
)

data class Format(
    val kota: String?,
    val tanggal: String?
)

data class Jadwal(
    val status: String?,
    val data: JadwalData?
)

data class JadwalData(
    val ashar: String?,
    val dzuhur: String?,
    val isya: String?,
    val maghrib: String?,
    val subuh: String?,
    val tanggal: String?,
)

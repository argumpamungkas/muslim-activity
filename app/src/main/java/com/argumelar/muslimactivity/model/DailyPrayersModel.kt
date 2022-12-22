package com.argumelar.muslimactivity.model

data class DailyPrayersModel(
    val id: Int?,
    val doa: String?,
    val ayat: String?,
    val latin: String?,
    val artinya: String?,
    var isExpandable: Boolean = false
)

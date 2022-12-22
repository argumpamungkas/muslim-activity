package com.argumelar.muslimactivity.model

data class AllCityResponse(
    val status: String?,
    val kota: List<SemuaKota>
)

data class SemuaKota(
    val id: String?,
    val nama: String
)

data class DetailKota(
    val kota: List<DataDetailKota>
)

data class DataDetailKota(
    val id: String?,
    val nama: String?
)

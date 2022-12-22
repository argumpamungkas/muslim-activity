package com.argumelar.muslimactivity.source.network

import com.argumelar.muslimactivity.model.*
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiEndpoint {

//    API JADWAL
    @GET("kota")
    suspend fun getAllCity() : AllCityResponse

    @GET("kota/kode/{kode}")
    suspend fun getDetailKota(
        @Path("kode") kode: Int
    ) : DetailKota

    @GET("jadwal/kota/{kota}/tanggal/{tanggal}")
    suspend fun getJadwalSholat(
        @Path("kota") kota : Int,
        @Path("tanggal") tanggal: String
    ) : JadwalSholat

//    API QURAN
    @GET("quran")
    suspend fun getQuran(): QuranResponse

    @GET("quran/{number}")
    suspend fun getDetailQuran(
        @Path("number") number : Int
    ): DetailQuranResponse

//    API DAILY
    @GET("api")
    suspend fun getDaily() : List<DailyPrayersModel>

}
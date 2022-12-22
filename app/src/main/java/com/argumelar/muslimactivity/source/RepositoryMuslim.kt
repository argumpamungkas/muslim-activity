package com.argumelar.muslimactivity.source

import com.argumelar.muslimactivity.model.*
import com.argumelar.muslimactivity.source.network.ApiServiceDaily
import com.argumelar.muslimactivity.source.network.ApiServiceJadwal
import com.argumelar.muslimactivity.source.network.ApiServiceQuran
import org.koin.dsl.module

val repositoryModule= module {
    factory { RepositoryMuslim() }
}

class RepositoryMuslim {

//    GET API JADWAL
    suspend fun fetchApiAllCity(): AllCityResponse {
        return ApiServiceJadwal.apiJadwal.getAllCity()
    }

    suspend fun fetchDetailKota(kode: Int): DetailKota {
        return ApiServiceJadwal.apiJadwal.getDetailKota(kode)
    }

    suspend fun fetchJadwalSholat(kota: Int, tanggal: String): JadwalSholat{
        return ApiServiceJadwal.apiJadwal.getJadwalSholat(kota, tanggal)
    }

//    GET API QURAN
    suspend fun fetchApiQuran(): QuranResponse {
        return ApiServiceQuran.apiQuran.getQuran()
    }

    suspend fun fetchApiDetailQuran(number: Int): DetailQuranResponse {
        return ApiServiceQuran.apiQuran.getDetailQuran(number)
    }

//    GET API DAILY
    suspend fun fetchApiDaily(): List<DailyPrayersModel> {
        return ApiServiceDaily.apiDaily.getDaily()
    }
}

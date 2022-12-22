package com.argumelar.muslimactivity.ui.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.argumelar.muslimactivity.model.AllCityResponse
import com.argumelar.muslimactivity.model.DetailKota
import com.argumelar.muslimactivity.model.JadwalSholat
import com.argumelar.muslimactivity.source.Constant
import com.argumelar.muslimactivity.source.RepositoryMuslim
import com.argumelar.muslimactivity.utils.PreferencesHelper
import kotlinx.coroutines.launch
import org.koin.dsl.module

val homeViewModelModule = module {
    factory { HomeViewModel(get(), get()) }
}

class HomeViewModel(private val repositoryMuslim: RepositoryMuslim, val context: Context) :
    ViewModel() {

    val sharedPref  = PreferencesHelper(context)

    val allCity by lazy { MutableLiveData<AllCityResponse>() }
    val kota by lazy { MutableLiveData<DetailKota>() }
    val jadwal by lazy { MutableLiveData<JadwalSholat>() }

    init {
        fetchAllCity()
    }

    fun fetchJadwal() {
        viewModelScope.launch {
            try {
                if (sharedPref.getBoolean(Constant.PREF_IS_UPDATE)){
                    val newKode = sharedPref.getInt(Constant.KEY_KODE)
                    val response = repositoryMuslim.fetchJadwalSholat(newKode, Constant.TANGGAL)
                    jadwal.value = response
                } else {
                    val response =
                        repositoryMuslim.fetchJadwalSholat(Constant.KODE_KOTA, Constant.TANGGAL)
                    jadwal.value = response
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchDetailKota() {
        viewModelScope.launch {
            try {
                if (sharedPref.getBoolean(Constant.PREF_IS_UPDATE)){
                    val newKode = sharedPref.getInt(Constant.KEY_KODE)
                    val response = repositoryMuslim.fetchDetailKota(newKode)
                    kota.value = response
                } else {
                    val response = repositoryMuslim.fetchDetailKota(Constant.KODE_KOTA)
                    kota.value = response
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchAllCity() {
        viewModelScope.launch {
            try {
                val response = repositoryMuslim.fetchApiAllCity()
                allCity.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun saveData(kode:Int){
        sharedPref.put(Constant.KEY_KODE, kode)
        sharedPref.put(Constant.PREF_IS_UPDATE, true)
    }
}
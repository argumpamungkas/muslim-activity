package com.argumelar.muslimactivity.ui.quran.quran

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.argumelar.muslimactivity.model.QuranModel
import com.argumelar.muslimactivity.model.QuranResponse
import com.argumelar.muslimactivity.source.RepositoryMuslim
import kotlinx.coroutines.launch
import org.koin.dsl.module

val quranViewModelModule = module {
    factory { QuranViewModel(get()) }
}

class QuranViewModel(
    private val repositoryMuslim: RepositoryMuslim
) : ViewModel() {


    val quran by lazy { MutableLiveData<QuranResponse>() }
    val message by lazy { MutableLiveData<String>() }

    init {
//        message.value = ""
        fetchQuran()
    }

    private fun fetchQuran(){
        viewModelScope.launch {
            try {
                val response = repositoryMuslim.fetchApiQuran()
                quran.value = response
//                message.value = "DIPANGGIL"
            } catch (e: Exception){
                e.printStackTrace()
//                message.value = "Terjadi Kesalahan"
            }
        }
    }

}
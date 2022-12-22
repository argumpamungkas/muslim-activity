package com.argumelar.muslimactivity.ui.dailyPrayers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.argumelar.muslimactivity.model.DailyPrayersModel
import com.argumelar.muslimactivity.source.RepositoryMuslim
import kotlinx.coroutines.launch
import org.koin.dsl.module

val dailyViewModelModule = module {
    factory { DailyViewModel(get()) }
}

class DailyViewModel(
    private val repoDaily: RepositoryMuslim
) : ViewModel() {


    val daily by lazy { MutableLiveData<List<DailyPrayersModel>>() }
//    val message by lazy {MutableLiveData<String>()}

    init {
//        message.value = ""
        fetchDaily()
    }

    private fun fetchDaily(){
        viewModelScope.launch {
            try {
                val response = repoDaily.fetchApiDaily()
                daily.value = response
//                message.value = "Dipanggil"
            } catch (e: Exception){
//                message.value = "Kesalahan"
            }
        }
    }

}
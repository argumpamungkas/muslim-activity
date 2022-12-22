package com.argumelar.muslimactivity.ui.quran.detail

import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.argumelar.muslimactivity.source.Constant
import com.argumelar.muslimactivity.source.RepositoryMuslim
import kotlinx.coroutines.launch
import com.argumelar.muslimactivity.model.DetailQuranResponse
import okio.IOException
import org.koin.core.KoinApplication.Companion.init
import org.koin.dsl.module

val detailQuranViewModelModule = module {
    factory { DetailQuranViewModel(get()) }
}

class DetailQuranViewModel(
    private val repositoryMuslim: RepositoryMuslim
) : ViewModel() {

    var mediaPlayer = MediaPlayer()

    val detailQuran by lazy { MutableLiveData<DetailQuranResponse>() }
    val play by lazy { MutableLiveData(0) }

    init {
        fetchDetail()
    }

    private fun fetchDetail() {
        viewModelScope.launch {
            try {
                val response = repositoryMuslim.fetchApiDetailQuran(Constant.NUMBER)
                detailQuran.value = response
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("error", "ERROR")
            }
        }
    }

    fun playSurah(url: String) {
        viewModelScope.launch {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
            if (!mediaPlayer.isPlaying) {
                Log.i("playSurah", "play")
                try {
                    mediaPlayer.setDataSource(url)
                    mediaPlayer.prepare()
                    mediaPlayer.start()
                    Log.i("playSurah", "play2")
                } catch (e: IOException) {
                    e.printStackTrace()
                    Log.i("playSurah", "Error play + ${play.value}")
                }
                play.value = 1
            } else {
                Log.i("playSurah", "Stop")
                try {
                    mediaPlayer.pause()
                    mediaPlayer.stop()
                    mediaPlayer.reset()
                } catch (e: IOException) {
                    e.printStackTrace()
                    Log.i("playSurah", "Error Stop + ${play.value}")
                }
                play.value = 0
            }
            mediaPlayer.setOnCompletionListener {
                mediaPlayer.pause()
                mediaPlayer.stop()
                mediaPlayer.reset()
                Log.i("playSurah", "COMPLETE")
                play.value = 0
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer.pause()
        mediaPlayer.stop()
        mediaPlayer.release()
        play.value = 0
    }
}
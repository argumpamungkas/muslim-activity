package com.argumelar.muslimactivity

import android.app.Application
import com.argumelar.muslimactivity.source.repositoryModule
import com.argumelar.muslimactivity.ui.*
import com.argumelar.muslimactivity.ui.dailyPrayers.dailyModule
import com.argumelar.muslimactivity.ui.dailyPrayers.dailyViewModelModule
import com.argumelar.muslimactivity.ui.home.homeModule
import com.argumelar.muslimactivity.ui.home.homeViewModelModule
import com.argumelar.muslimactivity.ui.quran.detail.detailQuranModule
import com.argumelar.muslimactivity.ui.quran.detail.detailQuranViewModelModule
import com.argumelar.muslimactivity.ui.quran.quran.quranModule
import com.argumelar.muslimactivity.ui.quran.quran.quranViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MuslimApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MuslimApp)
            modules(
                repositoryModule,
                splashModule,
                homeModule,
                homeViewModelModule,
                quranModule,
                quranViewModelModule,
                dailyModule,
                dailyViewModelModule,
                detailQuranModule,
                detailQuranViewModelModule
            )
        }
    }
}
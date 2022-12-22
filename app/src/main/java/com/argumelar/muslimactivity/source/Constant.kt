package com.argumelar.muslimactivity.source

import java.text.SimpleDateFormat
import java.util.*

class Constant {

    companion object {
        const val URL_JADWAL = "https://api.banghasan.com/sholat/format/json/"
        const val URL_QURAN = "https://quran-endpoint.vercel.app/"
        const val URL_DAILY_PRAYERS = "https://doa-doa-api-ahmadramadhan.fly.dev/"
        var NUMBER = 0

        val PREF_IS_UPDATE = "PREF_UPDATE"
        val KEY_KODE = "KODE"
        var KODE_KOTA = 679
        private val current = Calendar.getInstance().time
        private val day = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(current)!!
        var TANGGAL = day

        val Channel1 = "channel1"
    }



}
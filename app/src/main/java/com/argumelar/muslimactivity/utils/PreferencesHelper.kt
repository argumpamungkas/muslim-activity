package com.argumelar.muslimactivity.utils

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {

    private val PREFS_NAME = "id_city_detail123"
    private var sharedPref: SharedPreferences
    val editor: SharedPreferences.Editor

    init {
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }

//    MENYIMPAN DATA PADA PREFERENCES
    fun put(key: String, value: Int){
        editor.putInt(key, value)
            .apply()
    }

    fun put(key: String, value: Boolean){
        editor.putBoolean(key, value)
            .apply()
    }

//    MEMANGGIL DATA
    fun getInt(key: String): Int {
        return sharedPref.getInt(key, 0)
    }

    fun getBoolean(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }
}
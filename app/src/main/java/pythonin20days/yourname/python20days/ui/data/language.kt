package com.example.dersapp.data

import android.content.Context
import android.content.SharedPreferences

object LanguagePreference {
    private const val PREF_NAME = "app_prefs"
    private const val LANG_KEY = "selected_language"

    fun saveLanguage(context: Context, language: String) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(LANG_KEY, language).apply()
    }

    fun getLanguage(context: Context): String {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(LANG_KEY, "TR") ?: "TR" // Varsayılan değer Türkçe
    }
}
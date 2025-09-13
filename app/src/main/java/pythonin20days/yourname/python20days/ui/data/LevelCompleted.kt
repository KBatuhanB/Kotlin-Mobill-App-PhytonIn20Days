package com.example.dersapp.data

import android.content.Context

object SharedPreferencesHelper {

    private const val PREF_NAME = "LevelData"

    // Seviyenin tamamlandığını kaydeder
    fun setLevelCompleted(context: Context, level: Int) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("level_$level", true) // "level_1" gibi
        editor.apply()
    }

    // Seviyenin tamamlanıp tamamlanmadığını kontrol eder
    fun isLevelCompleted(context: Context, level: Int): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("level_$level", false) // Varsayılan olarak false döndürür
    }

    // Seviyenin ilerleme oranını kaydeder (örneğin, 0.8, 0.5 gibi)
    fun setLevelProgress(context: Context, level: Int, progress: Float) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putFloat("level_${level}_progress", progress) // "level_1_progress" gibi
        editor.apply()
    }

    // Seviyedeki ilerlemeyi okur
    fun getLevelProgress(context: Context, level: Int): Float {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getFloat("level_${level}_progress", 0f) // Varsayılan olarak 0f döndürür
    }
}
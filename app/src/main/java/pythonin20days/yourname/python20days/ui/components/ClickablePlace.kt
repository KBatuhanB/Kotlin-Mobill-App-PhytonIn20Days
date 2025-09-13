package com.example.dersapp.components

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun ClickablePlace(
    counter: Int,
    metinListesiSize: Int,
    metinIndex: Int,
    onCounterChange: (Int) -> Unit,
    onMetinIndexChange: (Int) -> Unit
) {
    val context = LocalContext.current

    fun vibrateForCounter() {
        val duration = 40L // counter arttıkça titreşim süresi uzasın

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            // Android 12 ve sonrası için VibratorManager
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            val vibrator = vibratorManager.defaultVibrator
            vibrator.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Android 8.0 ve sonrası için VibrationEffect ile
                vibrator.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                // Daha eski Android sürümleri için klasik titreşim
                @Suppress("DEPRECATION")
                vibrator.vibrate(duration)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Left half - Decrease counter
        Box(
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .fillMaxHeight()
                .align(Alignment.CenterStart)
                .clickable {
                    if (counter > 1) {
                        onCounterChange(counter - 1)
                        onMetinIndexChange((metinIndex - 1).mod(metinListesiSize))
                        vibrateForCounter()
                    }
                }
        )
        // Right half - Increase counter
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight()
                .align(Alignment.CenterEnd)
                .clickable {
                    if (counter < metinListesiSize) {
                        onCounterChange(counter + 1)
                        onMetinIndexChange((metinIndex + 1).mod(metinListesiSize))
                        vibrateForCounter()
                    }
                }
        )
    }
}
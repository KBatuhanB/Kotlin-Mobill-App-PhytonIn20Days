

package com.example.dersapp.components

import android.content.Context
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dersapp.data.DenemeFontFamily
import com.example.dersapp.data.SharedPreferencesHelper

@Composable
fun progressBar(
    metinListesiSize: Int,
    counter: Int,
    navController: NavController,
    nextLevelRoute: String,
    context: Context,
    level: Int,
    language: String
) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp

    val buttonHorizontalPadding = screenWidthDp * 0.06f
    val buttonVerticalPadding = screenHeightDp * 0.015f
    val boxPadding = screenWidthDp * 0.04f
    val progressBarHeight = screenHeightDp * 0.025f
    val iconSize = screenWidthDp * 0.06f

    val buttonText = when (language) {
        "TR" -> "Sonraki Bölüm"
        "EN" -> "Next Level"
        else -> ""
    }

    // Gerçek ilerleme oranı
    val targetProgress = (counter.toFloat() / metinListesiSize.toFloat()).coerceIn(0f, 1f)

    // Animasyonlu ilerleme değeri
    val animatedProgress by animateFloatAsState(
        targetValue = targetProgress,
        animationSpec = tween(durationMillis = 500),
        label = "progressAnimation"
    )

    // Seviye tamamlandı mı kontrolü
    LaunchedEffect(targetProgress) {
        if (targetProgress >= 1f) {
            SharedPreferencesHelper.setLevelCompleted(context, level)
        }
        SharedPreferencesHelper.setLevelProgress(context, level, targetProgress)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.BottomCenter)
                .padding(boxPadding)
        ) {
            if (animatedProgress < 1f) {
                LinearProgressIndicator(
                    progress = animatedProgress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(progressBarHeight)
                        .clip(RoundedCornerShape(progressBarHeight / 2)),
                    color = Color(0xFF70AE9F),
                    trackColor = Color.LightGray.copy(alpha = 0.3f)
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF73B1A2))
                        .clickable { navController.navigate(nextLevelRoute) },
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(screenWidthDp * 0.02f),
                        modifier = Modifier.padding(
                            vertical = buttonVerticalPadding,
                            horizontal = buttonHorizontalPadding
                        )
                    ) {
                        Text(
                            text = buttonText,
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontFamily = DenemeFontFamily,
                                fontWeight = FontWeight.Normal
                            ),
                            modifier = Modifier.padding(end = screenWidthDp * 0.01f)
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "İleri",
                            tint = Color.White,
                            modifier = Modifier.size(iconSize)
                        )
                    }
                }
            }
        }
    }
}




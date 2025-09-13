package com.example.dersapp.mainMenu

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dersapp.data.SharedPreferencesHelper

@Composable
fun LevelItem(
    level: Int,
    offsetX: Dp,
    verticalSpacing: Dp,
    context: Context,
    onClick: () -> Unit = {}
) {
    var butonlarGoster by remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp

    // Dinamik boyutlar
    val dynamicHorizontalPadding = (screenWidthDp * 0.33f).dp // örnek: 140dp yerine
    val dynamicButtonSize = (screenWidthDp * 0.19f).dp.coerceIn(46.dp, 100.dp) // örnek: 66dp yerine
    val dynamicShadow = (screenWidthDp * 0.012f).dp.coerceIn(2.dp, 12.dp)
    val dynamicFontSize = (screenWidthDp * 0.058f).coerceIn(16f, 28f).sp // örnek: 22sp yerine

    LaunchedEffect(Unit) {
        butonlarGoster = true
    }
    val isLevelCompleted = SharedPreferencesHelper.isLevelCompleted(context, level)

    AnimatedVisibility(
        visible = butonlarGoster,
        enter = fadeIn(animationSpec = tween(1000)) + slideInHorizontally(
            animationSpec = tween(1000),
            initialOffsetX = { -1000 }
        ),
        exit = fadeOut(animationSpec = tween(500)) + slideOutHorizontally(
            animationSpec = tween(500),
            targetOffsetX = { -300 }
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = offsetX, y = verticalSpacing)
                .padding(start = dynamicHorizontalPadding, end = dynamicHorizontalPadding)
                .shadow(dynamicShadow, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = onClick,
                modifier = Modifier
                    .size(dynamicButtonSize)
                    .clip(CircleShape),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isLevelCompleted)
                        Color(0xFF81C784) // soft green
                    else
                        Color(0xFF90A4AE) // blue gray
                ),
                contentPadding = PaddingValues()
            ) {
                BasicText(
                    text = level.toString(),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = dynamicFontSize,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}
package com.example.dersapp.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.dersapp.data.CodeDisplay

@SuppressLint("UnrememberedMutableState")
@Composable
fun ShowCode(
    code: String,
    startIndex: Int = 0,
    endIndex: Int = code.length,
    xRatio: Float = 0f,
    yRatio: Float = 0.94f,
    isVisible: Boolean = false,
) {
    var visible by remember { mutableStateOf(isVisible) }
    var codeBlockHeightPx by remember { mutableStateOf<Float?>(null) }

    LaunchedEffect(isVisible) {
        visible = isVisible
    }

    val displayCode = code.substring(
        startIndex.coerceAtLeast(0),
        endIndex.coerceAtMost(code.length)
    )

    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    val screenHeightPx = with(density) { configuration.screenHeightDp.dp.toPx() }
    val screenWidthPx = with(density) { configuration.screenWidthDp.dp.toPx() }

    val horizontalPadding = configuration.screenWidthDp.dp * 0.04f
    val codeBlockPadding = configuration.screenWidthDp.dp * 0.04f

    val xOffsetDp = with(density) { (screenWidthPx * xRatio).toDp() }
    val yOffsetTargetPx = screenHeightPx * yRatio

    // Kod kutusu ölçülene kadar çizme/animasyon başlatma
    if (codeBlockHeightPx != null) {
        // Animasyonun başlangıç ve bitiş noktası:
        // Görünmezken kutunun üstü ekranın altına denk gelmeli
        val hiddenOffsetPx = (screenHeightPx + codeBlockHeightPx!!)*1.15f
        val offsetY by animateFloatAsState(
            targetValue = if (visible) yOffsetTargetPx else hiddenOffsetPx,
            animationSpec = tween(
                durationMillis = 800,
                easing = FastOutSlowInEasing
            ),
            label = "slideAnimation"
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    // Sadece ilk ölçümde güncelle
                    if (codeBlockHeightPx == null || codeBlockHeightPx == 0f)
                        codeBlockHeightPx = coordinates.size.height.toFloat()
                }
                .offset(
                    x = xOffsetDp,
                    y = with(density) {
                        // Pivot alt, offset kutunun altına göre
                        (offsetY - codeBlockHeightPx!!).toDp()
                    }
                )
                .padding(horizontal = horizontalPadding)
        ) {
            CodeDisplay(
                code = displayCode,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(codeBlockPadding)
            )
        }
    } else {
        // İlk ölçüm için görünmez halde çiz, ölçüm alınsın diye
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    codeBlockHeightPx = coordinates.size.height.toFloat()
                }
                .offset(
                    x = xOffsetDp,
                    y = with(density) { (screenHeightPx).toDp() }
                )
                .padding(horizontal = horizontalPadding)
        ) {
            CodeDisplay(
                code = displayCode,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(codeBlockPadding)
            )
        }
    }
}
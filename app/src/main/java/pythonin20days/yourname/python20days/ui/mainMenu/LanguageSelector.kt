package com.example.dersapp.mainMenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp

@Composable
fun LanguageSelector(
    buttonImage: Painter,
    expanded: Boolean,
    onExpandChange: (Boolean) -> Unit,
    onLanguageSelected: (String) -> Unit
) {
    // Dinamik boyutlar
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp
    // Temel oran ayarları
    val baseButtonSize: Dp = (screenWidthDp * 0.07f).dp.coerceIn(20.dp, 40.dp)
    val basePadding: Dp = (screenWidthDp * 0.015f).dp.coerceAtLeast(3.dp)
    val borderWidth: Dp = (screenWidthDp * 0.003f).dp.coerceAtLeast(1.dp)
    val dropdownWidth: Dp = (screenWidthDp * 0.15f).dp.coerceIn(36.dp, 80.dp)
    val dropdownHeight: Dp = (screenHeightDp * 0.12f).dp.coerceIn(60.dp, 120.dp)
    val dropdownItemHeight: Dp = (screenHeightDp * 0.045f).dp.coerceIn(24.dp, 44.dp)
    val dropdownItemPadding: Dp = (screenWidthDp * 0.018f).dp.coerceAtLeast(3.dp)

    Box(
        modifier = Modifier
            .padding(basePadding)
            .size(baseButtonSize)
            .clip(CircleShape)
            .border(borderWidth, color = Color(206, 206, 206, 255), shape = CircleShape)
            .graphicsLayer(alpha = 0.8f)
    ) {
        Image(
            painter = buttonImage,
            contentDescription = "Language Button",
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
                .graphicsLayer { scaleX = 1f; scaleY = 1f },
            contentScale = ContentScale.Crop
        )

        Button(
            onClick = { onExpandChange(true) },
            modifier = Modifier
                .padding(basePadding)
                .size(baseButtonSize)
                .align(Alignment.CenterEnd),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(0.dp)
        ) {}

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandChange(false) },
            modifier = Modifier.size(dropdownWidth, dropdownHeight)
        ) {
            DropdownMenuItem(
                modifier = Modifier
                    .size(dropdownWidth, dropdownItemHeight)
                    .padding(horizontal = dropdownItemPadding)
                    .wrapContentSize(),
                text = { Text("TR") },
                onClick = { onLanguageSelected("TR") }
            )
            DropdownMenuItem(
                modifier = Modifier
                    .size(dropdownWidth, dropdownItemHeight)
                    .padding(horizontal = dropdownItemPadding)
                    .wrapContentSize(),
                text = { Text("EN") },
                onClick = { onLanguageSelected("EN") }
            )
        }
    }
}
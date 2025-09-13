package com.example.dersapp.components

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yourname.python20days.R
import com.example.dersapp.data.Screen

@Composable
fun lvlBackground(
    backgroundImage: Painter,
    navController: NavController
) {
    // Ekran bilgilerini al
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp

    // Oranlara göre dinamik değerler
    val buttonPadding: Dp = screenWidthDp * 0.02f          // örn. 8.dp
    val buttonSize: Dp = screenWidthDp * 0.1f               // örn. 40.dp
    val iconAlpha = 0.8f                                    // sabit bırakılabilir ama istersen oranlayabilirsin
    val scale = 1f                                          // scale sabit bırakıldı ama dinamik hale getirilebilir
    val contentPadding = PaddingValues(0.dp)                // iç padding sabit kaldı (görsel amaçlı uygun)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = backgroundImage,
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                },
            contentScale = ContentScale.Crop
        )

        Button(
            onClick = { navController.navigate(Screen.Home.route) },
            modifier = Modifier
                .padding(buttonPadding)
                .size(buttonSize),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            shape = CircleShape,
            contentPadding = contentPadding
        ) {
            Image(
                painter = painterResource(id = R.drawable.home),
                contentDescription = "Ana Sayfa",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(iconAlpha)
            )
        }
    }
}

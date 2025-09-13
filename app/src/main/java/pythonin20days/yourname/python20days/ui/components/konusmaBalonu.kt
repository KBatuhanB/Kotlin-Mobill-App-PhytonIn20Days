package com.example.dersapp.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.dersapp.levels.Level1
import com.example.dersapp.ui.theme.DersAppTheme
import com.yourname.python20days.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Ekranda konuşma balonu ve maskot görüntüleyen bir Composable fonksiyon.
 *
 * @param metin Konuşma balonunda gösterilecek metin
 * @param konumX Balonun yatay konumu (pixel cinsinden)
 * @param konumY Balonun dikey konumu (pixel cinsinden)
 * @param maskotResourceId Gösterilecek maskotun kaynak ID'si (varsayılan: R.drawable.maskothi)
 */
@Composable
fun konusmaBalonu(
    metin: String,
    maskotResourceId: Int = R.drawable.maskothi,
    isVisible: Boolean = false
) {
    var isFirstComposition by remember { mutableStateOf(true) }

    LaunchedEffect(isVisible) {
        if (isVisible) {
            isFirstComposition = false
        }
    }

    val konumX = 0f
    val konumY = 0f
    val maskot = painterResource(id = maskotResourceId)
    val konusmaBalonu = painterResource(id = R.drawable.konusmabalonu)

    var typedText by remember { mutableStateOf("") }
    var previousMetin by remember { mutableStateOf("") }
    val rotation = remember { Animatable(0f) }

    LaunchedEffect(metin) {
        if (metin != previousMetin) {
            previousMetin = metin
            typedText = ""
            launch {
                metin.forEachIndexed { i, char ->
                    typedText += char
                    delay(20)
                }
            }
            launch {
                rotation.snapTo(0f)
                rotation.animateTo(
                    targetValue = 5f,
                    animationSpec = repeatable(
                        iterations = 2,
                        animation = tween(25),
                        repeatMode = RepeatMode.Reverse
                    )
                )
                rotation.snapTo(0f)
            }
        }
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val ekranGenislik = maxWidth
        val ekranYukseklik = maxHeight

        val offsetX = ekranGenislik * konumX
        val offsetY = ekranYukseklik * konumY

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .offset(x = offsetX, y = offsetY)
                .graphicsLayer(alpha = 1f)
        ) {
            // Maskot
            AnimatedVisibility(
                visible = isVisible && !isFirstComposition,
                enter = slideInHorizontally(
                    animationSpec = tween(500, easing = {
                        val t = it - 1f
                        t * t * t + 1f
                    }),
                    initialOffsetX = { -it }
                ) + fadeIn(animationSpec = tween(400)),
                exit = slideOutHorizontally(
                    animationSpec = tween(500, easing = { it * it * it }),
                    targetOffsetX = { -it }
                ) + fadeOut(animationSpec = tween(400))
            ) {
                Image(
                    painter = maskot,
                    contentDescription = "Maskot karakteri",
                    modifier = Modifier
                        .size(ekranGenislik * 1.25f)  // boyut ekranın %25’i kadar
                        .align(Alignment.Center)
                        .offset(
                            x = -ekranGenislik * 0.3f,
                            y = ekranYukseklik * 0.27f
                        )
                        .graphicsLayer {
                            scaleX = -0.7f
                            scaleY = 0.7f
                        },
                    contentScale = ContentScale.Fit
                )
            }

            // Konuşma Balonu
            AnimatedVisibility(
                visible = isVisible && !isFirstComposition,
                enter = fadeIn(tween(400, delayMillis = 100)) +
                        scaleIn(tween(400, delayMillis = 100), initialScale = 0.8f),
                exit = fadeOut(tween(300)) +
                        scaleOut(tween(300), targetScale = 0.8f)
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center)
                        .graphicsLayer {
                            rotationZ = rotation.value
                        }
                ) {
                    Image(
                        painter = konusmaBalonu,
                        contentDescription = "",
                        modifier = Modifier
                            .size(ekranGenislik * 1f, ekranYukseklik * 1f)
                            .align(Alignment.Center)
                            .offset(
                                x = -ekranGenislik * 0f,
                                y = ekranYukseklik * -0.22f
                            ),
                        contentScale = ContentScale.Fit
                    )
                }

                Box(
                    modifier = Modifier
                        .width(ekranGenislik * 0.8f)
                        .height(ekranYukseklik * 0.18f)
                        .align(Alignment.BottomCenter)
                        .offset(
                            y = ekranYukseklik * 0.135f,
                            x = ekranGenislik * 0.1f
                        )
                        .padding(
                            horizontal = ekranGenislik * 0.03f,  // ekranın %5'i kadar yatay padding
                        )
                ) {
                    Text(
                        text = typedText,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = (ekranGenislik.value * 0.06f).sp,
                            lineHeight = (ekranGenislik.value * 0.06f).sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
                        ),
                        textAlign = TextAlign.Start,
                        overflow = TextOverflow.Visible,
                        softWrap = true,
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentHeight(Alignment.CenterVertically)
                            .padding(
                                horizontal = ekranGenislik * 0.03f,  // ekranın %5'i kadar yatay padding
                                vertical = ekranYukseklik * 0.03f   // ekranın %2'si kadar dikey padding
                            )
                    )
                }
            }
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun PreLevelDetailScreen() {
    val navController = rememberNavController()
    DersAppTheme {
        Level1(navController, "TR")
    }
}


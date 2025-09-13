package com.example.dersapp.mainMenu

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yourname.python20days.R
import com.example.dersapp.data.LanguagePreference
import com.example.dersapp.data.navigateToLevel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.sin

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LevelScreen(navController: NavController, totalLevels: Int, context: Context) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp

    // Dinamik yerleşim değerleri
    val outerPadding = (screenWidthDp * 0.04f).dp // 16.dp yerine
    val verticalHeaderPadding = (screenHeightDp * 0.03f).dp // 24.dp yerine
    val horizontalHeaderPadding = (screenWidthDp * 0.04f).dp // 16.dp yerine
    val headerTextPadding = (screenWidthDp * 0.025f).dp // 10.dp yerine
    val headerFontSize = (screenWidthDp * 0.08f).coerceIn(22f, 36f).sp // 30.sp yerine
    val spacerWidth = (screenWidthDp * 0.02f).dp // 8.dp yerine
    val columnInnerPadding = (screenWidthDp * 0.04f).dp // 16.dp yerine
    val columnTopSpacer = (screenHeightDp * 0.025f).dp // 20.dp yerine
    val verticalSpacing = (screenHeightDp * 0.055f).dp // 45.dp yerine
    val horizontalSpacingFactor = 0.27f // 200.dp yaklaşık olarak 0.27 * screenWidthDp
    val horizontalSpacing = (screenWidthDp * horizontalSpacingFactor).dp
    val endHeaderSizeW = (screenWidthDp * 0.25f).dp // 90.dp yerine
    val endHeaderSizeH = (screenHeightDp * 0.13f).dp // 100.dp yerine
    val endHeaderTopPadding = (screenHeightDp * 0.025f).dp // 20.dp yerine
    val endHeaderFontSize = (screenWidthDp * 0.063f).coerceIn(18f, 30f).sp // 24.sp yerine
    val endHeaderTextTopPadding = (screenHeightDp * 0.025f).dp // 20.dp yerine

    val scrollState = rememberScrollState()
    val backgroundColor = remember { mutableStateOf(Color(239, 238, 237, 255)) }
    var baslik by remember { mutableStateOf(false) }
    var bitis by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    val buttonImage: Painter
    var dil by remember { mutableStateOf(if (LanguagePreference.getLanguage(context) == "TR") 1 else 2) }
    var lang by remember { mutableStateOf(LanguagePreference.getLanguage(context)) }

    buttonImage = if (dil == 1) {
        painterResource(id = R.drawable.tr1)
    } else {
        painterResource(id = R.drawable.en)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(46, 124, 148, 255), Color(14, 14, 14, 255))
                    )
                )
        ) {
            AnimatedHeader(visible = baslik) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = verticalHeaderPadding, horizontal = horizontalHeaderPadding)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color(0xFF1C4C5B),
                                shape = MaterialTheme.shapes.medium
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.width(spacerWidth))
                        BasicText(
                            modifier = Modifier.padding(headerTextPadding),
                            text = if (lang == "TR") "Kodlamaya Başla" else "Start Coding",
                            style = TextStyle(
                                fontSize = headerFontSize,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        )
                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                    ) {
                        LanguageSelector(
                            buttonImage = buttonImage,
                            expanded = expanded,
                            onExpandChange = { expanded = it },
                            onLanguageSelected = { langCode ->
                                expanded = false
                                dil = if (langCode == "TR") 1 else 2
                                lang = langCode
                                LanguagePreference.saveLanguage(context, langCode)
                            }
                        )
                    }
                }
            }

            val displayedLevels = remember { mutableStateListOf<Int>() }
            val scope = rememberCoroutineScope()
            var counter by remember { mutableStateOf(1) }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(columnInnerPadding)
            ) {
                Spacer(modifier = Modifier.height(columnTopSpacer))

                val levelsPerScreen = 10

                displayedLevels.forEach { level ->
                    val offsetX = calculateSnakeOffset(level, levelsPerScreen) * (horizontalSpacing.value / 2)

                    LevelItem(
                        level = level,
                        offsetX = offsetX.dp,
                        verticalSpacing = 0.dp,
                        context = context,
                        onClick = { navigateToLevel(navController, level, lang) },
                    )

                    if (level < totalLevels) {
                        Spacer(modifier = Modifier.height(verticalSpacing))
                    }
                }
            }

            LaunchedEffect(Unit) {
                val progress = (scrollState.value.toFloat() / scrollState.maxValue).coerceIn(0f, 1f)
                backgroundColor.value = Color.DarkGray.copy(alpha = 1f - progress)
                baslik = true
                scope.launch {
                    for (level in 1..totalLevels) {
                        displayedLevels.add(level)
                        delay(150L)
                        counter++
                        if (level == totalLevels) {
                            bitis = true
                        }
                    }
                }
            }

            AnimatedHeader(visible = bitis) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .size(endHeaderSizeW, endHeaderSizeH)
                        .padding(top = endHeaderTopPadding),
                    contentAlignment = Alignment.Center
                ) {
                    BasicText(
                        modifier = Modifier.padding(top = endHeaderTextTopPadding),
                        text = if (lang == "TR") "Tebrikler! \uD83C\uDF89" else "Congratulations! \uD83C\uDF89",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = endHeaderFontSize,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}

fun calculateSnakeOffset(level: Int, levelsPerScreen: Int): Float {
    val waveFrequency = 2 * PI / levelsPerScreen
    return sin(level * waveFrequency).toFloat() * if (level % levelsPerScreen == 0) 0f else 1f
}

@Composable
fun AnimatedHeader(visible: Boolean, content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(1000)) + slideInVertically(
            animationSpec = tween(1000),
            initialOffsetY = { -300 }
        ),
        exit = fadeOut(animationSpec = tween(500)) + slideOutHorizontally(
            animationSpec = tween(500),
            targetOffsetX = { -300 }
        )
    ) {
        content()
    }
}
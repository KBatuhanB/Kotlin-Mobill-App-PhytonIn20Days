package com.example.dersapp.components

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.CircleShape
import com.yourname.python20days.R
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeOutputQuestion(
    question: String,
    codeSnippet: String,
    solution: String,
    isVisible: Boolean,
    modifier: Modifier = Modifier,
    onAnswerChange: (String) -> Unit = {},
    onDismissRight: () -> Unit = {},
    onDismissLeft: () -> Unit = {}
) {
    var userAnswer by remember { mutableStateOf("") }
    var isSubmitted by remember { mutableStateOf(false) }
    val isCorrect = remember(userAnswer, isSubmitted) {
        isSubmitted && userAnswer.trim().equals(solution.trim(), ignoreCase = true)
    }

    var containerWidth by remember { mutableStateOf(0f) }

    // Ekran bilgisi ve dp dönüşümü için
    val configuration = LocalConfiguration.current
    val screenWidthPx = configuration.screenWidthDp
    val screenHeightPx = configuration.screenHeightDp
    val density = LocalDensity.current

    val topPaddingDp = (screenHeightPx * 0.08f).dp
    val bottomPaddingDp = (screenHeightPx * 0.15f).dp
    val iconSizeDp = (screenWidthPx * 0.1f).dp
    val paddingHorizontalDp = (screenWidthPx * 0.04f).dp
    val paddingVerticalSmallDp = (screenHeightPx * 0.02f).dp
    val paddingVerticalMediumDp = (screenHeightPx * 0.03f).dp
    val textFieldHeightDp = (screenHeightPx * 0.15f).dp
    val cardPaddingDp = (screenWidthPx * 0.04f).dp

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInHorizontally(
            initialOffsetX = { it / 2 },
            animationSpec = tween(700, easing = FastOutSlowInEasing)
        ) + fadeIn(animationSpec = tween(700)) + scaleIn(
            initialScale = 0.9f,
            animationSpec = tween(700)
        ),
        exit = fadeOut(animationSpec = tween(400))
    ) {
        // Kod Alanı
        AnimatedVisibility(visible = codeSnippet.isNotBlank()) {
            ShowCode(code = codeSnippet, yRatio = 0.5f, isVisible = true)
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxSize()
                .padding(top = topPaddingDp)
                .onGloballyPositioned { coordinates ->
                    containerWidth = coordinates.size.width.toFloat()
                }
                .pointerInput(isSubmitted) {
                    detectTapGestures { offset ->
                        if (isSubmitted) {
                            if (offset.x < containerWidth * 0.3f) {
                                onDismissLeft()
                            } else {
                                onDismissRight()
                            }
                        }
                    }
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingHorizontalDp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Soru
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = paddingVerticalMediumDp)
                        .background(
                            color = Color(0xFFFDE9EC),
                            shape = MaterialTheme.shapes.medium
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.maskotkitapokuyan),
                        contentDescription = null,
                        modifier = Modifier
                            .size(iconSizeDp)
                            .padding(end = paddingHorizontalDp)
                    )
                    Text(
                        text = question,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        ),
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                }

            }

            // CEVAP GÖSTERGESİ (Card) - ALTTA
            AnimatedVisibility(
                visible = isSubmitted,
                enter = fadeIn() + scaleIn(),
                exit = fadeOut() + scaleOut(),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(0.92f)
                    .padding(bottom = bottomPaddingDp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isCorrect) Color(0xFFE8F5E9) else Color(0xFFFFEBEE)
                    )
                ) {
                    Column(modifier = Modifier.padding(cardPaddingDp)) {
                        Text(
                            text = if (isCorrect) "✅" else "❌",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = if (isCorrect) Color(0xFF2E7D32) else Color(0xFFC62828),
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        if (!isCorrect) {
                            Text(
                                text = solution,
                                color = Color.Black,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = paddingVerticalSmallDp)
                            )
                        }
                    }
                }
            }

            // CEVAP GİRİŞ ALANI (Card) - ALTTA, Card görünmüyorsa
            if (!isSubmitted) {
                Surface(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth(0.9f)
                        .padding(bottom = bottomPaddingDp),
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.medium,
                    tonalElevation = 4.dp,
                    shadowElevation = 8.dp
                ) {
                    Column(modifier = Modifier.padding(paddingHorizontalDp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Çıktıyı Yazın:",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.weight(1f)
                            )
                            IconButton(
                                onClick = {
                                    if (userAnswer.isNotBlank()) {
                                        isSubmitted = true
                                        onAnswerChange(userAnswer)
                                    }
                                },
                                enabled = userAnswer.isNotBlank(),
                                modifier = Modifier
                                    .size(iconSizeDp)
                                    .background(
                                        if (userAnswer.isNotBlank()) MaterialTheme.colorScheme.primary
                                        else MaterialTheme.colorScheme.surfaceVariant,
                                        CircleShape
                                    )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowForward,
                                    contentDescription = "Gönder",
                                    tint = if (userAnswer.isNotBlank())
                                        MaterialTheme.colorScheme.onPrimary
                                    else
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        TextField(
                            value = userAnswer,
                            onValueChange = { userAnswer = it },
                            placeholder = { Text("Buraya yazınız...") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(textFieldHeightDp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun CodeOutputQuestionPreview() {
    MaterialTheme {
        CodeOutputQuestion(
            question = "Aşağıdaki kodun çıktısı nedir?",
            codeSnippet = "fun main() {\n    val x = 5\n    val y = 3\n    println(x + y)\n}",
            solution = "8",
            isVisible = true,
            onAnswerChange = { /* Handle answer change */ },
            onDismissLeft = { /* Handle left dismiss */ },
            onDismissRight = { /* Handle right dismiss */ }
        )
    }
}
package com.example.dersapp.components

import androidx.compose.runtime.Composable
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.ui.layout.onGloballyPositioned
import com.yourname.python20days.R
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity

@Composable
fun FillInTheBlankQuestion(
    question: String,
    codeSnippet: String,
    correctAnswers: List<String>,
    answerCount: Int,
    isVisible: Boolean,
    onDismissRight: () -> Unit = {},
    onDismissLeft: () -> Unit = {}
) {
    val userAnswers = remember { mutableStateListOf(*Array(answerCount) { "" }) }
    var isSubmitted by remember { mutableStateOf(false) }
    var containerWidth by remember { mutableStateOf(0f) }

    val isCorrectList = remember(userAnswers, isSubmitted) {
        userAnswers.mapIndexed { index, answer ->
            isSubmitted && answer.trim() == correctAnswers.getOrNull(index)?.trim()
        }
    }

    // Dinamik boşluklar ve boyutlar
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp

    // Oranlar (bunları ihtiyaca göre ince ayar yapabilirsin)
    val topPaddingDp = (screenHeightDp * 0.08f).dp
    val horizontalPaddingDp = (screenWidthDp * 0.04f).dp
    val verticalPaddingSmallDp = (screenHeightDp * 0.015f).dp
    val verticalPaddingMediumDp = (screenHeightDp * 0.03f).dp
    val textFieldHeightDp = (screenHeightDp * 0.07f).dp
    val cardPaddingDp = (screenWidthDp * 0.04f).dp
    val iconSizeDp = (screenWidthDp * 0.1f).dp
    val imageSizeDp = (screenWidthDp * 0.13f).dp
    val rowBottomPaddingDp = (screenHeightDp * 0.015f).dp
    val questionFontSizeSp = (screenWidthDp * 0.045f).sp
    val answerFontSizeSp = (screenWidthDp * 0.036f).sp
    val iconPaddingStartDp = (screenWidthDp * 0.013f).dp
    val answerBoxHeightDp = (screenHeightDp * 0.046f).dp
    val answerBoxPaddingHorizontalDp = (screenWidthDp * 0.02f).dp
    val answerBoxPaddingVerticalDp = (screenHeightDp * 0.005f).dp
    val answerIndexWidthDp = (screenWidthDp * 0.07f).dp
    val betweenAnswersHorizontalDp = (screenWidthDp * 0.04f).dp
    val betweenAnswersVerticalDp = (screenHeightDp * 0.01f).dp
    val cardAnswerPaddingTopDp = (screenHeightDp * 0.01f).dp
    val cardPaddingVerticalDp = (screenHeightDp * 0.018f).dp
    val submitButtonHeightDp = (screenHeightDp * 0.09f).dp
    val submitButtonFontSizeSp = (screenWidthDp * 0.045f).sp
    val submitButtonHorizontalPaddingDp = (screenWidthDp * 0.04f).dp
    val submitButtonVerticalPaddingDp = (screenHeightDp * 0.01f).dp

    // Dinamik border ve elevation değerleri
    val borderWidthDp = (screenWidthDp * 0.003f).dp.coerceAtLeast(1.dp) // min 1.dp
    val tonalElevationDp = (screenHeightDp * 0.005f).dp.coerceIn(2.dp, 8.dp)
    val shadowElevationDp = (screenHeightDp * 0.01f).dp.coerceIn(4.dp, 16.dp)
    val buttonDefaultElevationDp = (screenHeightDp * 0.01f).dp.coerceIn(4.dp, 16.dp)
    val buttonPressedElevationDp = (screenHeightDp * 0.015f).dp.coerceIn(6.dp, 24.dp)

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

        ShowCode(code = codeSnippet, isVisible = isVisible)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
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
                    .padding(horizontal = horizontalPaddingDp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Soru
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = rowBottomPaddingDp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = MaterialTheme.shapes.medium
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.maskotkitapokuyan),
                        contentDescription = null,
                        modifier = Modifier
                            .size(imageSizeDp)
                            .padding(end = horizontalPaddingDp)
                    )
                    Text(
                        text = question,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = questionFontSizeSp
                        ),
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(verticalPaddingMediumDp))

                // Cevap alanları
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = -(screenHeightDp * 0f).dp),
                    color = Color.White,
                    shape = MaterialTheme.shapes.medium,
                    tonalElevation = tonalElevationDp,
                    shadowElevation = shadowElevationDp
                ) {
                    Column(
                        modifier = Modifier.padding(cardPaddingDp),
                        verticalArrangement = Arrangement.spacedBy(betweenAnswersVerticalDp)
                    ) {
                        for (rowIndex in 0 until (answerCount + 1) / 2) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(betweenAnswersHorizontalDp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                for (colIndex in 0..1) {
                                    val answerIndex = rowIndex * 2 + colIndex
                                    if (answerIndex < answerCount) {
                                        val isCorrect = isSubmitted && isCorrectList[answerIndex]
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            Text(
                                                text = "${answerIndex + 1}.",
                                                style = MaterialTheme.typography.bodyMedium,
                                                modifier = Modifier.width(answerIndexWidthDp)
                                            )
                                            var text by remember { mutableStateOf("") }
                                            Box(
                                                modifier = Modifier
                                                    .height(answerBoxHeightDp)
                                                    .border(
                                                        width = borderWidthDp,
                                                        color = if (!isSubmitted) MaterialTheme.colorScheme.primary
                                                        else if (isCorrect) Color(0xFF43A047)
                                                        else Color(0xFFE53935),
                                                        shape = RoundedCornerShape(4.dp)
                                                    )
                                                    .background(Color.White, RoundedCornerShape(4.dp))
                                                    .padding(
                                                        horizontal = answerBoxPaddingHorizontalDp,
                                                        vertical = answerBoxPaddingVerticalDp
                                                    ),
                                                contentAlignment = Alignment.CenterStart
                                            ) {
                                                BasicTextField(
                                                    value = if (isSubmitted && !isCorrect) correctAnswers[answerIndex]
                                                    else userAnswers[answerIndex],
                                                    onValueChange = {
                                                        userAnswers[answerIndex] = it
                                                    },
                                                    modifier = Modifier
                                                        .fillMaxSize()
                                                        .padding(vertical = answerBoxPaddingVerticalDp),
                                                    textStyle = TextStyle(
                                                        fontSize = answerFontSizeSp,
                                                        color = Color.Black
                                                    ),
                                                    enabled = !isSubmitted,
                                                    singleLine = true,
                                                    decorationBox = { innerTextField ->
                                                        if ((userAnswers[answerIndex].isEmpty()) && !isSubmitted) {
                                                            Text(
                                                                text = "Cevabınız",
                                                                fontSize = answerFontSizeSp,
                                                                color = Color.Gray
                                                            )
                                                        }
                                                        Box(
                                                            modifier = Modifier.fillMaxHeight(),
                                                            contentAlignment = Alignment.CenterStart
                                                        ) {
                                                            innerTextField()
                                                        }
                                                    }
                                                )
                                            }

                                            if (isSubmitted) {
                                                Icon(
                                                    painter = painterResource(
                                                        id = if (isCorrect) R.drawable.tr1
                                                        else R.drawable.en
                                                    ),
                                                    contentDescription = if (isCorrect) "Doğru" else "Yanlış",
                                                    tint = if (isCorrect) Color(0xFF2E7D32) else Color(
                                                        0xFFC62828
                                                    ),
                                                    modifier = Modifier
                                                        .size(iconSizeDp * 0.5f)
                                                        .padding(start = iconPaddingStartDp)
                                                )
                                            }
                                        }
                                    } else {
                                        Spacer(modifier = Modifier.weight(1f))
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(verticalPaddingMediumDp))

                // Cevap Göstergesi
                AnimatedVisibility(visible = isSubmitted) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isCorrectList.all { it }) Color(0xFFE8F5E9)
                            else Color(0xFFFFEBEE)
                        )
                    ) {
                        Column(modifier = Modifier.padding(cardPaddingDp, vertical = cardPaddingVerticalDp)) {
                            Text(
                                text = if (isCorrectList.all { it }) "✅"
                                else "❌",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    color = if (isCorrectList.all { it }) Color(0xFF2E7D32)
                                    else Color(0xFFC62828),
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            if (!isCorrectList.all { it }) {
                                Text(
                                    text = "${correctAnswers.joinToString(", ")}",
                                    color = Color.Black,
                                    modifier = Modifier.padding(top = cardAnswerPaddingTopDp)
                                )
                            }
                        }
                    }
                }

                // Gönder Butonu
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = submitButtonHorizontalPaddingDp,
                            vertical = submitButtonVerticalPaddingDp
                        ),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    this@Column.AnimatedVisibility(
                        visible = !isSubmitted && userAnswers.all { it.isNotBlank() },
                        enter = fadeIn(animationSpec = tween(400)) + scaleIn(
                            initialScale = 0.95f,
                            animationSpec = tween(400)
                        ),
                        exit = fadeOut(animationSpec = tween(200)) + scaleOut(
                            targetScale = 0.95f,
                            animationSpec = tween(200)
                        )
                    ) {
                        Button(
                            onClick = { isSubmitted = true },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(submitButtonHeightDp),
                            shape = MaterialTheme.shapes.large,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = buttonDefaultElevationDp,
                                pressedElevation = buttonPressedElevationDp
                            )
                        ) {
                            Text(
                                text = "Cevapları Kontrol Et",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = submitButtonFontSizeSp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun FiilInTheBlankQuestionPreview() {
    MaterialTheme {
        FillInTheBlankQuestion(
            question = "Aşağıdaki kodun çıktısı ne olur?",
            codeSnippet = """
        fun main() {
            val a = 5
            val b = 3
            val result = a + b
            println(result)
            a
            a
            
            A
            a
            
        }
    """.trimIndent(),
            correctAnswers = listOf("5", "3", "8"),
            answerCount = 6,
            isVisible = true,
            onDismissLeft = { /* sola geç */ },
            onDismissRight = { /* sağa geç */ }
        )
    }
}
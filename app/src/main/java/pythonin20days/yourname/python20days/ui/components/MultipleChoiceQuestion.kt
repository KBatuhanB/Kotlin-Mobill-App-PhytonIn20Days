package com.example.dersapp.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.yourname.python20days.R
import kotlinx.coroutines.delay

@Composable
fun MultipleChoiceQuestion(
    question: String,
    codeSnippet: String = "",
    options: List<String>,
    correctAnswerIndex: Int,
    solution: String,
    isVisible: Boolean,
    modifier: Modifier = Modifier,
    onAnswerSelected: (Int) -> Unit = {},
    onDismissRight: () -> Unit = {},
    onDismissLeft: () -> Unit = {}
) {
    // Statik (sabit) boşluklar ve yerleşim değerleri
    val outerVerticalPadding = 8.dp
    val topPadding = 50.dp
    val questionRowVertical = 8.dp
    val questionRowHorizontal = 8.dp
    val questionRowInnerPadding = 12.dp
    val maskotSize = 40.dp
    val maskotEndPadding = 12.dp
    val questionTextFontSize = 18.sp
    val questionTextLineHeight = 22.sp
    val optionsCardVerticalPadding = 8.dp
    val optionsCardBorderWidth = 3.dp
    val optionsCardInnerPadding = 12.dp
    val optionLetterEndPadding = 12.dp
    val optionLetterFontSize = 16.sp
    val optionTextFontSize = 15.sp
    val optionTextLineHeight = 20.sp
    val solutionBoxHorizontal = 16.dp
    val solutionBoxVertical = 20.dp
    val solutionBoxInnerPadding = 16.dp
    val solutionBoxFontSize = 16.sp
    val solutionBoxLineHeight = 20.sp
    val solutionBoxSpacer = 24.dp
    val optionsColumnVertical = 4.dp
    val optionsColumnHorizontal = 16.dp
    val codeSectionTopSpacer = 32.dp
    val codeSectionBottomSpacer = 16.dp

    var selectedOption by remember { mutableStateOf(-1) }
    var isCorrect by remember { mutableStateOf<Boolean?>(null) }
    val visibleOptions = remember { mutableStateListOf<Boolean>() }

    // Görünürlük değiştiğinde animasyonları başlat
    LaunchedEffect(isVisible) {
        if (isVisible) {
            visibleOptions.clear()
            options.forEachIndexed { index, _ ->
                delay(100)
                visibleOptions.add(true)
            }
        }
    }
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInHorizontally(
            initialOffsetX = { it / 2 },
            animationSpec = tween(700, easing = FastOutSlowInEasing)
        ) + fadeIn(animationSpec = tween(700)) +
                scaleIn(initialScale = 0.9f, animationSpec = tween(700)),
        exit = fadeOut(animationSpec = tween(400))
    ) {
        // Kod alanı
        if (codeSnippet.isNotBlank()) {
            ShowCode(code = codeSnippet, isVisible = true)
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(vertical = outerVerticalPadding)
                .padding(top = topPadding)
                .verticalScroll(rememberScrollState())
                .pointerInput(Unit){
                    detectTapGestures { tapOffset ->
                        if (tapOffset.x < size.width *0.3 && selectedOption != -1) {
                            onDismissLeft()
                        } else if(selectedOption != -1){
                            onDismissRight()
                        }
                    }
                }
        ) {
            // Soru
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = questionRowVertical, horizontal = questionRowHorizontal)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = questionRowHorizontal, vertical = questionRowVertical)
                        .background(
                            color = Color(0xFFFDE9EC),
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(questionRowInnerPadding)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.maskotkitapokuyan),
                        contentDescription = null,
                        modifier = Modifier.size(maskotSize).padding(end = maskotEndPadding)
                    )
                    Text(
                        text = question,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = questionTextFontSize,
                            lineHeight = questionTextLineHeight,
                            color = Color.Black
                        ),
                        modifier = Modifier.weight(1f, fill = false)
                    )
                }
            }


            // Şıklar
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.4f)
                    .padding(horizontal = optionsColumnHorizontal, vertical = optionsColumnVertical)
            ) {
                options.forEachIndexed { index, option ->
                    val isSelected = selectedOption == index
                    val isAnswerCorrect = isCorrect == true && isSelected
                    val isAnswerIncorrect = isCorrect == false && isSelected
                    val isCorrectAnswer = isCorrect != null && index == correctAnswerIndex

                    val borderColor = when {
                        isAnswerCorrect -> Color(0xFF4CAF50)  // Green for correct answer
                        isAnswerIncorrect -> Color(0xFFF44336) // Red for wrong selection
                        isCorrectAnswer && isCorrect == false -> Color(0xFF4CAF50) // Green for correct answer when wrong is selected
                        isSelected -> MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.outline
                    }


                    val animatedBorderColor by animateColorAsState(
                        targetValue = borderColor,
                        animationSpec = tween(durationMillis = 500),
                        label = "borderColorAnimation"
                    )

                    val cardBackgroundColor = when {
                        isAnswerCorrect -> Color(0x994CAF50)  // Light green for correct answer
                        isAnswerIncorrect -> Color(0x99F44336)  // Light red for wrong selection
                        isCorrectAnswer && isCorrect == false -> Color(0x994CAF50) // Light green for correct answer when wrong is selected
                        else -> Color(253, 233, 236, 255)  // Semi-transparent white
                    }

                    AnimatedVisibility(
                        visible = visibleOptions.getOrNull(index) == true,
                        enter = slideInVertically(initialOffsetY = { it / 2 }, animationSpec = tween(500)) +
                                fadeIn(animationSpec = tween(500))
                    ) {
                        OutlinedCard(
                            onClick = {
                                if (isCorrect == null) {
                                    selectedOption = index
                                    val correct = index == correctAnswerIndex
                                    isCorrect = correct
                                    onAnswerSelected(index)
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = optionsCardVerticalPadding)
                                .border(
                                    width = optionsCardBorderWidth,
                                    color = animatedBorderColor,
                                    shape = MaterialTheme.shapes.medium
                                )
                                .scale(if (isSelected) 1.02f else 1f)
                                .height(IntrinsicSize.Min),
                            colors = CardDefaults.cardColors(
                                containerColor = cardBackgroundColor
                            )
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(optionsCardInnerPadding)
                            ) {
                                Text(
                                    text = "${'A' + index}.",
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontSize = optionLetterFontSize,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = Modifier.padding(end = optionLetterEndPadding),
                                    color = Color(0xFF000000)
                                )
                                Text(
                                    text = option,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontSize = optionTextFontSize,
                                        lineHeight = optionTextLineHeight
                                    ),
                                    color = Color(0xFF1A1A1A)
                                )
                            }
                        }
                    }
                }

                // Çözüm metni
                AnimatedVisibility(
                    visible = isCorrect != null,
                    enter = slideInVertically(initialOffsetY = { it / 2 }, animationSpec = tween(500)) + fadeIn(),
                    exit = fadeOut()
                ) {
                    Spacer(modifier = Modifier.height(solutionBoxSpacer))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = solutionBoxHorizontal, vertical = solutionBoxVertical)
                            .background(
                                color = Color(0xFFFDE9EC),
                                shape = MaterialTheme.shapes.medium
                            )
                            .padding(solutionBoxInnerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (isCorrect == true) "✅ $solution" else "❌ $solution",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = if (isCorrect == true) Color(0xFF4CAF50) else Color(0xFFF44336),
                                fontSize = solutionBoxFontSize,
                                lineHeight = solutionBoxLineHeight,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier.fillMaxWidth(0.9f)
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = false)
@Composable
fun MultipleChoiceQuestionPreview() {
    MultipleChoiceQuestion(
        question = "Aşağıdaki kodun çıktısı nedir?",
        codeSnippet = """
        elma = 3
        if elma > 2:
            print("Elma çok!")  
        -> Çıktı: Elma çok! <-
    
        elma = 1
        if elma > 2:
            print("Elma çok!")  
        -> Çıktı: (yok) <-
    """.trimIndent(),
        options = listOf("5", "10", "25", "Hata verir"),
        correctAnswerIndex = 1,
        solution = "x 5, 2 ile çarpılınca 10 olur",
        onAnswerSelected = {},
        isVisible = true ,
        onDismissRight = {},
        onDismissLeft = {}
    )
}
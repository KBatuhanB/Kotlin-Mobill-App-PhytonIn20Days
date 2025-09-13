package com.example.dersapp.levels

import com.example.dersapp.data.Screen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.yourname.python20days.R
import com.example.dersapp.components.ClickablePlace
import com.example.dersapp.components.CodeOutputQuestion
import com.example.dersapp.components.MultipleChoiceQuestion
import com.example.dersapp.components.konusmaBalonu
import com.example.dersapp.components.lvlBackground
import com.example.dersapp.components.ShowCode
import com.example.dersapp.components.progressBar

@Composable
fun Level12(navController: NavController, language: String) {

    //----------------------------------------------------------------------------- Variables

    val backGroundImage = painterResource(id = R.drawable.bolumarkaplani)
    val maskotKobot = R.drawable.maskotornegin
    var metinIndex by remember { mutableStateOf(0) }
    var counter by remember { mutableStateOf(1) }
    val context = LocalContext.current
    val level = 12

    //----------------------------------------------------------------------------- Lesson Texts

    val metinListesi = when (language) {
        "TR" -> listOf(
            "Bugün Python'da mantıksal operatörleri öğreneceğiz: and, or, not.",
            "Bu operatörler, birden fazla koşulu birleştirmek için kullanılır.",
            "'and' her iki koşul da doğruysa True verir.",
            "'or' koşullardan biri doğruysa True verir.",
            "'not' bir koşulun tersini alır. True ise False yapar.",
            "Birlikte bazı örnekleri inceleyelim."
        )
        "EN" -> listOf(
            "Today we will learn logical operators in Python: and, or, not.",
            "These operators are used to combine multiple conditions.",
            "'and' returns True only if both conditions are true.",
            "'or' returns True if at least one condition is true.",
            "'not' reverses the condition. If it's True, it becomes False.",
            "Let's look at some examples together."
        )
        else -> emptyList()
    }

    //----------------------------------------------------------------------------- ClickablePlace (Navigation)

    ClickablePlace(
        counter,
        metinListesi.size + 5,
        metinIndex,
        { counter = it },
        { if (counter <= metinListesi.size) metinIndex = it }
    )

    //----------------------------------------------------------------------------- Background

    lvlBackground(backGroundImage, navController)

    //----------------------------------------------------------------------------- Speech Bubble

    val balonMetni = if (metinIndex in metinListesi.indices) metinListesi[metinIndex] else ""

    konusmaBalonu(balonMetni, maskotKobot, counter <= metinListesi.size)

    //----------------------------------------------------------------------------- Code Examples

    val code1 = """
        a = 5
        b = 10
        print(a < 10 and b > 5)
        -> True <-
    """.trimIndent()
    ShowCode(code1, isVisible = metinIndex == 2)

    val code2 = """
        a = 3
        b = 7
        print(a > 5 or b == 7)
        -> True <-
    """.trimIndent()
    ShowCode(code2, isVisible = metinIndex == 3)

    val code3 = """
        a = 4
        print(not (a == 4))
        -> False <-
    """.trimIndent()
    ShowCode(code3, isVisible = metinIndex == 4)

    //----------------------------------------------------------------------------- Questions

    CodeOutputQuestion(
        question = if (language == "TR")
            "Bu kodun çıktısı nedir?"
        else
            "What is the output of this code?",
        codeSnippet = """
            x = 6
            y = 3
            print(x > 5 and y < 2)
        """.trimIndent(),
        solution = "False",
        isVisible = counter == metinListesi.size + 1,
        onAnswerChange = {},
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    CodeOutputQuestion(
        question = if (language == "TR")
            "Bu kodun çıktısı nedir?"
        else
            "What is the output of this code?",
        codeSnippet = """
            x = 4
            y = 8
            print(not(x > 2 or y < 5))
        """.trimIndent(),
        solution = "False",
        isVisible = counter == metinListesi.size + 2,
        onAnswerChange = {},
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "Aşağıdakilerden hangisi sadece her iki koşul da doğruysa True olur?"
        else
            "Which one is True only if both conditions are True?",
        options = listOf("and", "or", "not","elif"),
        correctAnswerIndex = 0,
        solution = if (language == "TR") "\"and\" operatörü her iki koşul da doğruysa True verir." else "\"and\" operator returns True only if both conditions are True.",
        isVisible = counter == metinListesi.size + 3,
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "not operatörü ne işe yarar?"
        else
            "What does the 'not' operator do?",
        options = listOf(
            if (language == "TR") "Koşulun tersini alır" else "Reverses the condition",
            if (language == "TR") "Koşulları birleştirir" else "Combines conditions",
            if (language == "TR") "Her zaman True verir" else "Always returns True",
            if (language == "TR") "Her zaman False verir" else "Always returns False"
        ),
        correctAnswerIndex = 0,
        solution = if (language == "TR") "\"not\" operatörü bir koşulun tersini alır. Koşul doğruysa False, yanlışsa True verir." else "\"not\" operator reverses the condition. If it's True, it becomes False.",
        isVisible = counter == metinListesi.size + 4,
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    //----------------------------------------------------------------------------- Closing Text

    val closingText = when (language) {
        "TR" -> "Süper! Artık mantıksal operatörlerle karmaşık koşullar yazabilirsin."
        "EN" -> "Awesome! Now you can write complex conditions using logical operators."
        else -> ""
    }
    konusmaBalonu(closingText, maskotKobot, counter == metinListesi.size + 5)

    //----------------------------------------------------------------------------- Progress Bar

    progressBar(
        metinListesi.size + 5,
        counter,
        navController,
        Screen.lvl13.createRoute(language),
        context,
        level,
        language
    )
}
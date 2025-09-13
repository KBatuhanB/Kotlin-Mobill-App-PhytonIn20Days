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
import com.example.dersapp.components.FillInTheBlankQuestion
import com.example.dersapp.components.konusmaBalonu
import com.example.dersapp.components.lvlBackground
import com.example.dersapp.components.ShowCode
import com.example.dersapp.components.progressBar

@Composable
fun Level11(navController: NavController, language: String) {

    //----------------------------------------------------------------------------- Variables

    val backGroundImage = painterResource(id = R.drawable.bolumarkaplani)
    val maskotKobot = R.drawable.maskothi
    var metinIndex by remember { mutableStateOf(0) }
    var counter by remember { mutableStateOf(1) }
    val context = LocalContext.current
    val level = 11

    //----------------------------------------------------------------------------- Lesson Texts

    val metinListesi = when (language) {
        "TR" -> listOf(
            "Merhaba! Bugün Python'da karşılaştırma operatörlerini öğreneceğiz.",
            "Bu operatörler, iki değeri karşılaştırmak için kullanılır ve True veya False sonucu üretir.",
            "İşte yaygın karşılaştırma operatörleri: ==, !=, >, <, >=, <=",
            "'==' iki değerin eşit olup olmadığını kontrol eder.",
            "'!=' iki değerin eşit olmadığını kontrol eder.",
            "'>' ve '<' büyüktür ve küçüktür anlamına gelir.",
            "'>=' ve '<=' ise büyük eşit ve küçük eşit anlamına gelir.",
            "Şimdi birlikte bir örnekleri inceleyelim."
        )
        "EN" -> listOf(
            "Hello! Today we will learn about comparison operators in Python.",
            "These operators are used to compare two values and return either True or False.",
            "Here are the common comparison operators: ==, !=, >, <, >=, <=",
            "'==' checks if two values are equal.",
            "'!=' checks if two values are not equal.",
            "'>' and '<' mean greater than and less than.",
            "'>=' and '<=' mean greater than or equal to, and less than or equal to.",
            "Now let's look at examples together."
        )
        else -> emptyList()
    }

    //----------------------------------------------------------------------------- ClickablePlace (Navigation)

    ClickablePlace(
        counter,
        metinListesi.size + 3,
        metinIndex,
        { counter = it },
        { if (counter <= metinListesi.size) metinIndex = it }
    )

    //----------------------------------------------------------------------------- Background

    lvlBackground(backGroundImage, navController)

    //----------------------------------------------------------------------------- Speech Bubble

    val balonMetni = if (metinIndex in metinListesi.indices) metinListesi[metinIndex] else ""

    konusmaBalonu(balonMetni, maskotKobot, counter <= metinListesi.size)

    //----------------------------------------------------------------------------- Code Example

    val codeExample = """
            a = 10
            b = 5
            print(a == b)
            -> False <-
        """.trimIndent()
    ShowCode(codeExample, isVisible = metinIndex == 3)

    val codeExample2 = """
            a = 10
            b = 5
            print(a != b)
            -> True <-
        """.trimIndent()
    ShowCode(codeExample2, isVisible = metinIndex == 4)

    val codeExample3 = """
            a = 10
            b = 5
            print(a < b)
            print(a > b)
            -> False <-
            -> True  <-
        """.trimIndent()
    ShowCode(codeExample3, isVisible = metinIndex == 5)

    val codeExample4 = """
            a = 10
            b = 10
            print(a <= b)
            -> True  <-
        """.trimIndent()
    ShowCode(codeExample4, isVisible = metinIndex == 6)

    //----------------------------------------------------------------------------- Questions

    FillInTheBlankQuestion(
        question = if (language == "TR")
            "Aşağıdaki boşluklara uygun karşılaştırma operatörlerini yazın."
        else
            "Fill in the blanks with the correct comparison operators.",
        codeSnippet = """
            print(5 == 5)
            -> 1.____ <-
            print(3 > 7)
            -> 2.____ <-
        """.trimIndent(),
        correctAnswers = listOf("True", "False"),
        answerCount = 2,
        isVisible = counter == metinListesi.size + 1,
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    CodeOutputQuestion(
        question = if (language == "TR")
            "Aşağıdaki kodun çıktısı nedir?"
        else
            "What is the output of the following code?",
        codeSnippet = """
            x = 8
            y = 10
            
            if (x <= y)
               print(x)
            else
               print(y)
        """.trimIndent(),
        solution =  "8",
        isVisible = counter == metinListesi.size + 2,
        onAnswerChange = {},
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    //----------------------------------------------------------------------------- Closing Text

    val closingText = when (language) {
        "TR" -> "Harika! Artık karşılaştırma operatörlerini biliyorsun ve karşılaştırmalar yapabilirsin!"
        "EN" -> "Great! Now you know how to use comparison operators and make comparisons!"
        else -> ""
    }
    konusmaBalonu(closingText, maskotKobot, counter == metinListesi.size + 3)

    //----------------------------------------------------------------------------- Progress Bar

    progressBar(
        metinListesi.size + 3,
        counter,
        navController,
        Screen.lvl12.createRoute(language),
        context,
        level,
        language
    )
}
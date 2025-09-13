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
fun Level9(navController: NavController, language: String) {

    //----------------------------------------------------------------------------- Variables

    val backGroundImage = painterResource(id = R.drawable.bolumarkaplani)
    val maskotKobot = R.drawable.maskotpc
    var metinIndex by remember { mutableStateOf(0) }
    var counter by remember { mutableStateOf(1) }
    val context = LocalContext.current
    val level = 9

    //----------------------------------------------------------------------------- Lesson Texts

    val metinListesi = when (language) {
        "TR" -> listOf(
            "Merhaba! Bugün Python'da koşullu ifadeleri öğreneceğiz.",
            "Koşullu ifadeler programların karar vermesini sağlar.",
            "\"If-else\" yapısı, bir şart doğruysa bir kodu, yanlışsa başka bir kodu çalıştırmamızı sağlar.",
            "Örneğin, yaşın 18 veya daha büyük olup olmadığını kontrol etmek için if-else kullanabiliriz.",
            "Şimdi aşağıdaki kod örneğine bakalım, satır satır açıklayacağım.",
            "1. Satırda 'yas' değişkenine 18 değeri atandı.",
            "2. Satırda 'if' ile 'yas'ın 18 veya daha büyük olup olmadığı kontrol edilir.",
            "3. Satırda, şart doğruysa \"Reşitsiniz.\" yazdırılır.",
            "4. Satırda 'else' ile şartın yanlış olması durumunda yapılacak işlem belirtilir.",
            "5. Satırda, şart yanlışsa \"Reşit değilsiniz.\" yazdırılır.",
            "Bu şekilde if-else yapısı kullanarak programımızın akışını koşula göre değiştirebiliriz. Şimdi sorulara geçelim."
        )

        "EN" -> listOf(
            "Hello! Today we'll learn conditional statements in Python.",
            "Conditionals allow programs to make decisions.",
            "The \"if-else\" structure lets us run some code if a condition is true, and other code if it is false.",
            "For example, we can check if age is 18 or older using if-else.",
            "Let's look at the code below and I'll explain it line by line.",
            "Line 1 assigns the value 18 to the variable 'age'.",
            "Line 2 uses 'if' to check if age is 18 or older.",
            "Line 3 prints \"You are an adult.\" if the condition is true.",
            "Line 4 is the 'else' block, for when the condition is false.",
            "Line 5 prints \"You are not an adult.\" if the condition is false.",
            "This way, we can control the program flow using if-else based on conditions. Now let's move on to the questions."
        )

        else -> emptyList()
    }

    //----------------------------------------------------------------------------- ClickablePlace (Navigation)

    ClickablePlace(
        counter,
        metinListesi.size + 3,
        metinIndex,
        { counter = it },
        {if (counter <= metinListesi.size) metinIndex = it}
    )

    //----------------------------------------------------------------------------- Background

    lvlBackground(backGroundImage, navController)

    //----------------------------------------------------------------------------- Speech Bubble

    val balonMetni = if (metinIndex in metinListesi.indices) metinListesi[metinIndex] else ""

    konusmaBalonu(balonMetni, maskotKobot, counter <= metinListesi.size)

    //----------------------------------------------------------------------------- Code Example

    val codeExample = when (language) {
        "TR" -> """
            yas = 18
            if yas >= 18:
                print("Reşitsiniz.")
            else:
                print("Reşit değilsiniz.")
        """.trimIndent()

        "EN" -> """
            age = 18
            if age >= 18:
                print("You are an adult.")
            else:
                print("You are not an adult.")
        """.trimIndent()

        else -> ""
    }

    ShowCode(codeExample, isVisible = metinIndex in 3..9)


    //----------------------------------------------------------------------------- Questions


    CodeOutputQuestion(
        question = if (language == "TR")
            "Aşağıdaki kodun çıktısı nedir?"
        else
            "What is the output of the following code?",
        codeSnippet = if (language == "TR")
            """
            yas = 15
            if yas >= 18:
                print("Reşitsiniz")
            else:
                print("Reşit değilsiniz")
        """.trimIndent()
        else """
            age = 18
            if age >= 18:
                print("You are an adult")
            else:
                print("You are not an adult")
        """.trimIndent(),
        solution = if (language == "TR") "Reşit değilsiniz" else "You are not an adult",
        isVisible = counter == metinListesi.size + 2,
        onAnswerChange = {},
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    FillInTheBlankQuestion(
        question = if (language == "TR")
            "Aşağıdaki kodda eksik olan anahtar kelimeleri doldurun:"
        else
            "Fill in the missing keywords in the code below:",
        codeSnippet = if (language == "TR") """
        yas = 15
        (1.____) yas >= 18:
            print("Reşitsiniz")
        (2.____):
            print("Reşit değilsiniz")
    """.trimIndent() else """
        age = 15
        (1.____) age >= 18:
            print("You are an adult")
        (2.____):
            print("You are not an adult")
    """.trimIndent(),
        correctAnswers = listOf("if", "else"),
        answerCount = 2,
        isVisible = counter == metinListesi.size + 1,
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    //----------------------------------------------------------------------------- Kapanis Konusmasi

    val closingText = when (language) {
        "TR" -> "Harika! Artık Python'da koşullu ifadelerle temel karar yapıları kurmayı öğrendin!"
        "EN" -> "Great! Now you have learned how to create basic decision structures using conditional statements in Python!"
        else -> ""
    }
    konusmaBalonu(closingText, maskotKobot, counter == metinListesi.size + 3)


    //----------------------------------------------------------------------------- Progress Bar

    progressBar(
        metinListesi.size + 3,
        counter,
        navController,
        Screen.lvl10.createRoute(language),
        context,
        level,
        language
    )
}

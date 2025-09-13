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
fun Level10(navController: NavController, language: String) {

    //----------------------------------------------------------------------------- Variables

    val backGroundImage = painterResource(id = R.drawable.bolumarkaplani)
    val maskotKobot = R.drawable.maskotok
    var metinIndex by remember { mutableStateOf(0) }
    var counter by remember { mutableStateOf(1) }
    val context = LocalContext.current
    val level = 10

    //----------------------------------------------------------------------------- Lesson Texts

    val metinListesi = when (language) {
        "TR" -> listOf(
            "Merhaba! Bugün Python'da çoklu karar yapılarından bahsedeceğiz.",
            "\"if\" ve \"else\" yapılarının yanı sıra \"elif\" anahtar kelimesi ile birden fazla durumu kontrol edebiliriz.",
            "Birden fazla elif kullanarak farklı durumlara farklı kod blokları çalıştırabiliriz.",
            "Örneğin, not sisteminde öğrencinin aldığı puana göre farklı mesajlar yazdırabiliriz.",
            "Şimdi aşağıdaki kod örneğine bakalım, satır satır açıklayacağım.",
            "1. Satırda 'not' değişkenine 75 değeri atandı.",
            "2. Satırda 'if' ile notun 90 veya daha yüksek olup olmadığı kontrol edilir.",
            "3. Satırda, şart doğruysa \"Çok İyi\" yazdırılır.",
            "4. Satırda 'elif' ile notun 75 veya daha yüksek olup olmadığı kontrol edilir.",
            "5. Satırda, şart doğruysa \"İyi\" yazdırılır.",
            "6. Satırda ikinci bir 'elif' ile notun 50 veya daha yüksek olup olmadığı kontrol edilir.",
            "7. Satırda, şart doğruysa \"Orta\" yazdırılır.",
            "8. Son olarak 'else' ile tüm diğer durumlar için \"Kaldınız\" yazdırılır.",
            "Bu şekilde birden fazla elif kullanarak çoklu karar yapıları oluşturabiliriz. Şimdi sorulara geçelim."
        )
        "EN" -> listOf(
            "Hello! Today we'll talk about multiple decision structures in Python.",
            "Besides \"if\" and \"else\", we can use \"elif\" to check multiple conditions.",
            "Using multiple elif statements, we can run different code blocks for different cases.",
            "For example, we can print different messages based on a student's grade.",
            "Let's look at the code below and I'll explain it line by line.",
            "Line 1 assigns the value 75 to the variable 'grade'.",
            "Line 2 uses 'if' to check if the grade is 90 or above.",
            "Line 3 prints \"Excellent\" if the condition is true.",
            "Line 4 uses 'elif' to check if the grade is 75 or above.",
            "Line 5 prints \"Good\" if the condition is true.",
            "Line 6 uses a second 'elif' to check if the grade is 50 or above.",
            "Line 7 prints \"Average\" if the condition is true.",
            "Line 8 finally uses 'else' to print \"Failed\" for all other cases.",
            "This way, we can create multiple decision branches using several elif statements. Now let's move on to the questions."
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

    val codeExample = when (language) {
        "TR" -> """
            not_ = 75
            if not_ >= 90:
                print("Çok İyi")
            elif not_ >= 75:
                print("İyi")
            elif not_ >= 50:
                print("Orta")
            else:
                print("Kaldınız")
        """.trimIndent()
        "EN" -> """
            grade = 75
            if grade >= 90:
                print("Excellent")
            elif grade >= 75:
                print("Good")
            elif grade >= 50:
                print("Average")
            else:
                print("Failed")
        """.trimIndent()
        else -> ""
    }

    ShowCode(codeExample, isVisible = metinIndex in 3..12)

    //----------------------------------------------------------------------------- Questions

    CodeOutputQuestion(
        question = if (language == "TR")
            "Aşağıdaki kodun çıktısı nedir?"
        else
            "What is the output of the following code?",
        codeSnippet = if (language == "TR")
            """
            not_ = 60
            if not_ >= 90:
                print("Çok İyi")
            elif not_ >= 75:
                print("İyi")
            elif not_ >= 50:
                print("Orta")
            else:
                print("Kaldınız")
            """.trimIndent()
        else
            """
            grade = 60
            if grade >= 90:
                print("Excellent")
            elif grade >= 75:
                print("Good")
            elif grade >= 50:
                print("Average")
            else:
                print("Failed")
            """.trimIndent(),
        solution = if (language == "TR") "Orta" else "Average",
        isVisible = counter == metinListesi.size + 2,
        onAnswerChange = {},
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    FillInTheBlankQuestion(
        question = if (language == "TR")
            "Aşağıdaki kodda boş bırakılan yerlere uygun anahtar kelimeleri yazın:"
        else
            "Fill in the missing keywords in the code below:",
        codeSnippet = if (language == "TR") """
            not_ = 80
            1.____ not_ >= 90:
                print("Çok İyi")
            2.____ not_ >= 75:
                print("İyi")
            3.____:
                print("Kaldınız")
        """.trimIndent() else """
            grade = 80
            1.____ grade >= 90:
                print("Excellent")
            2.____ grade >= 75:
                print("Good")
            3.____:
                print("Failed")
        """.trimIndent(),
        correctAnswers = listOf("if", "elif", "else"),
        answerCount = 3,
        isVisible = counter == metinListesi.size + 1,
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    //----------------------------------------------------------------------------- Closing Text

    val closingText = when (language) {
        "TR" -> "Harika! Artık Python'da elif ile çoklu karar yapıları kurmayı öğrendin!"
        "EN" -> "Great! Now you have learned how to use multiple elif statements to create complex decision structures in Python!"
        else -> ""
    }
    konusmaBalonu(closingText, maskotKobot, counter == metinListesi.size + 3)

    //----------------------------------------------------------------------------- Progress Bar

    progressBar(
        metinListesi.size + 3,
        counter,
        navController,
        Screen.lvl11.createRoute(language),
        context,
        level,
        language
    )
}

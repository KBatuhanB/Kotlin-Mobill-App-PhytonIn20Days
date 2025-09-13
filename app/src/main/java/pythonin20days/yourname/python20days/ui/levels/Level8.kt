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
import com.example.dersapp.components.konusmaBalonu
import com.example.dersapp.components.lvlBackground
import com.example.dersapp.components.ShowCode
import com.example.dersapp.components.progressBar

@Composable
fun Level8(navController: NavController, language: String) {

    //----------------------------------------------------------------------------- Varaibles

    val backGroundImage = painterResource(id = R.drawable.bolumarkaplani)
    val maskotKobot = R.drawable.maskotbook
    var metinIndex by remember { mutableStateOf(0) }
    var counter by remember { mutableStateOf(1) }
    val context = LocalContext.current
    val level = 8

    val metinListesi = when (language) {
        "TR" -> listOf(
            "Merhaba! Bugün Python'da aritmetik işlemleri öğreneceğiz.",
            "Toplama (+), çıkarma (-), çarpma (*), bölme (/) işlemleri çok temel ve önemlidir. Python'da bu işlemler için işlem önceliği kuralları vardır.",
            "Ek olarak, mod alma (%) işlemi kalanı verir.",
            "Örneğin 10 % 3 işlemi 1 sonucunu verir çünkü 10, 3'e bölündüğünde kalan 1'dir.",
            "Ayrıca / ile yapılan bölme ondalıklı sonuç verirken // tam sayı bölme yapar.",
            "Şimdi örneklere geçelim!"
        )
        "EN" -> listOf(
            "Hello! Today we'll learn arithmetic operations in Python.",
            "Addition (+), subtraction (-), multiplication (*), and division (/) are very basic and important. In Python, there are rules for operator precedence.",
            "In addition, the modulo (%) operator gives the remainder.",
            "For example, 10 % 3 results in 1 because 10 divided by 3 leaves a remainder of 1.",
            "Also, / gives decimal division, while // gives integer division.",
            "Now let's move on to the examples!"
        )
        else -> emptyList()
    }

    //----------------------------------------------------------------------------- ClickablePlace

    ClickablePlace(
        counter,
        metinListesi.size + 4,
        metinIndex,
        { counter = it },
        { if (counter <= metinListesi.size) metinIndex = it }
    )

    //----------------------------------------------------------------------------- ArkaPlan

    lvlBackground(backGroundImage, navController)

    //----------------------------------------------------------------------------- KonusmaBaloinu

    val balonMetni = if (metinIndex in metinListesi.indices) metinListesi[metinIndex] else ""
    konusmaBalonu(balonMetni, maskotKobot, counter <= metinListesi.size)

    //----------------------------------------------------------------------------- Kodlar

    val code1 = when (language) {
        "TR" -> """
            print(5 + 3)
            print(5 - 3)
            print(5 * 3)
            print(5 / 2)
            
            -> 8   <-
            -> 2   <-
            -> 15  <-
            -> 2.5 <-
        """.trimIndent()
        "EN" -> """
            print(5 + 3)
            
            -> 8 <-
        """.trimIndent()
        else -> ""
    }
    ShowCode(code = code1, isVisible = metinIndex == 1)

    val code2 = when (language) {
        "TR" -> """
            print(10 % 3)
            
            -> 1 <-
        """.trimIndent()
        "EN" -> """
            print(10 % 3)
            
            -> 1 <-
        """.trimIndent()
        else -> ""
    }
    ShowCode(code = code2, isVisible = metinIndex == 3)

    val code3 = when (language) {
        "TR" -> """
            print(10 / 3)
            
            -> 3.333... <-
        """.trimIndent()
        "EN" -> """
            print(10 / 3)
            
            -> 3.333... <-
        """.trimIndent()
        else -> ""
    }
    ShowCode(code = code3, isVisible = metinIndex == 4)

    val code4 = when (language) {
        "TR" -> """
            print(10 // 3)
            
            -> 3 <-
        """.trimIndent()
        "EN" -> """
            print(10 // 3)
            
            -> 3 <-
        """.trimIndent()
        else -> ""
    }
    ShowCode(code = code4, isVisible = metinIndex == 4)

    //----------------------------------------------------------------------------- Sorular

    CodeOutputQuestion(
        question = if (language == "TR")
            "Aşağıdaki kodun çıktısı nedir?"
        else
            "What is the output of the following code?",
        codeSnippet = """
            print(7 // 4)
        """.trimIndent(),
        solution = "1",
        isVisible = counter == metinListesi.size + 1,
        onAnswerChange = {},
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    CodeOutputQuestion(
        question = if (language == "TR")
            "Aşağıdaki kodun çıktısı nedir?"
        else
            "What is the output of the following code?",
        codeSnippet = """
            print(15 % 6)
        """.trimIndent(),
        solution = "3",
        isVisible = counter == metinListesi.size + 2,
        onAnswerChange = {},
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    CodeOutputQuestion(
        question = if (language == "TR")
            "Aşağıdaki kodun çıktısı nedir?"
        else
            "What is the output of the following code?",
        codeSnippet = """
            print(9 - 2 * ( 7 // 2 ))
        """.trimIndent(),
        solution = "3",
        isVisible = counter == metinListesi.size + 3,
        onAnswerChange = {},
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    //----------------------------------------------------------------------------- Kapanis Konusmasi

    val closingText = when (language) {
        "TR" -> "Harika! Artık Python'da aritmetik işlemler konusunda çok daha bilgilisin!"
        "EN" -> "Great! Now you know much more about arithmetic operations in Python!"
        else -> ""
    }
    konusmaBalonu(closingText, maskotKobot, counter == metinListesi.size + 4)

    //----------------------------------------------------------------------------- Progress Bar

    progressBar(
        metinListesi.size + 4,
        counter,
        navController,
        Screen.lvl9.createRoute(language),
        context,
        level,
        language
    )
}

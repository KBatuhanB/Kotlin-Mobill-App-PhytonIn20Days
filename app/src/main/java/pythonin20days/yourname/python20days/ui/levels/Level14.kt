package com.example.dersapp.levels

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.yourname.python20days.R
import com.example.dersapp.components.*
import com.example.dersapp.data.Screen

@Composable
fun Level14(navController: NavController, language: String) {

    //---------------------------------------------------------------------------- Variables

    val backGroundImage = painterResource(id = R.drawable.bolumarkaplani)
    val maskotKobot = R.drawable.maskotpc
    var metinIndex by remember { mutableStateOf(0) }
    var counter by remember { mutableStateOf(1) }
    val context = LocalContext.current
    val level = 14

    //---------------------------------------------------------------------------- Texts

    val metinListesi = when (language) {
        "TR" -> listOf(
            "Bugünkü konumuz: Python'da `for` döngüsü ve `range()` fonksiyonu.",
            "`for` döngüsü, belirli sayıda veya belirli aralıktaki işlemleri tekrar tekrar yapmamızı sağlar.",
            "Örneğin, 1'den 5'e kadar sayıları yazdırmak istiyorsak `for` çok kullanışlıdır.",
            "`range(start, stop, step)` fonksiyonu ile döngünün başlayacağı, biteceği ve artış miktarını belirleyebiliriz.",
            "Örnek olarak: `for i in range(1, 6):` demek, i değişkenini 1'den 5'e kadar (6 hariç) döndür demektir.",
            "Şimdi adım adım bir örneğe bakalım:",
            "1️⃣ `for i in range(1, 4):` \n→ i 1, 2 ve 3 değerlerini alacak.",
            "2️⃣ `print(i)` \n→ Her değer için i'yi ekrana yazdıracak.",
            "Sonuç olarak ekranda 1, 2 ve 3 sayıları satır satır yazdırılır.",
            "Önceki derste öğrendiğimiz `while` döngüsüne göre `for` döngüsü daha kısa ve okunabilirdir.",
            "`range()`'in üçüncü parametresi olan `step` ile artış miktarını belirleyebiliriz:",
            "`range(0, 10, 2)` ifadesi 0'dan 10'a kadar 2 şer 2 şer artar: 0, 2, 4, 6, 8.",
            "Artık `for` ve `range` kullanarak tekrarlama işlemlerini nasıl yapacağımızı biliyoruz.",
            "Şimdi bazı sorulara bakalım ve öğrendiklerimizi pekiştirelim."
        )

        "EN" -> listOf(
            "Today’s topic: Python `for` loops and the `range()` function.",
            "`for` loops allow us to repeat actions a specific number of times or over a specific range.",
            "For example, if we want to print numbers from 1 to 5, `for` is very handy.",
            "With the function `range(start, stop, step)`, we set where the loop starts, ends, and the increment step.",
            "For example: `for i in range(1, 6):` means i will take values 1 to 5 (6 excluded).",
            "Let's look at an example step-by-step:",
            "1️⃣ `for i in range(1, 4):` \n→ i will be 1, 2, and 3.",
            "2️⃣ `print(i)` \n→ Prints i for each value.",
            "So the output will be numbers 1, 2, and 3 printed on separate lines.",
            "Compared to the `while` loop we learned earlier, `for` loops are shorter and more readable.",
            "The third parameter of `range()`, called `step`, defines the increment amount:",
            "`range(0, 10, 2)` means counting from 0 to 10 by 2's: 0, 2, 4, 6, 8.",
            "Now we know how to use `for` and `range` to perform repetitive tasks.",
            "Let’s look at some questions to practice what we've learned."
        )

        else -> emptyList()
    }

    //---------------------------------------------------------------------------- Click Control

    ClickablePlace(
        counter,
        metinListesi.size + 5,
        metinIndex,
        { counter = it },
        { if (counter <= metinListesi.size) metinIndex = it }
    )

    //---------------------------------------------------------------------------- Background

    lvlBackground(backGroundImage, navController)

    //---------------------------------------------------------------------------- Speech Bubble

    val balonMetni = if (metinIndex in metinListesi.indices) metinListesi[metinIndex] else ""

    konusmaBalonu(balonMetni, maskotKobot, counter <= metinListesi.size)

    //---------------------------------------------------------------------------- Code Block (Visual Example)

    val forCode = """
        for i in range(1, 4):
            print(i)
        
        -> 1 <-
        -> 2 <-
        -> 3 <-
    """.trimIndent()

    ShowCode(forCode, isVisible = metinIndex in 5..9)

    val forCode2 = """
        for i in range(0, 10, 2):
            print(i)
        
        -> 0 <-
        -> 2 <-
        -> 4 <-
        -> 6 <-
        -> 8 <-
    """.trimIndent()

    ShowCode(forCode2, isVisible = metinIndex in 10..12)

    //---------------------------------------------------------------------------- Questions

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "Aşağıdaki kod ne kadar 'Merhaba' yazdırır?"
        else
            "How many times does the following code print 'Hello'?",
        codeSnippet = if (language == "TR")
            """
            for i in range(3):
                print("Merhaba")
            """.trimIndent()
        else
            """
            for i in range(3):
                print("Hello")
            """.trimIndent(),
        options = if (language == "TR")
            listOf(
                "1 kez",
                "3 kez",
                "Sonsuz kez",
                "Hiç yazdırmaz"
            )
        else
            listOf(
                "1 time",
                "3 times",
                "Infinite times",
                "Does not print"
            ),
        correctAnswerIndex = 1,
        solution = if (language == "TR")
            "`range(3)` ifadesi 0'dan başlayarak 3'e kadar (3 hariç) 3 sayı üretir, dolayısıyla döngü 3 kere çalışır."
        else
            "`range(3)` generates numbers from 0 up to 3 (excluding 3), so the loop runs 3 times.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 1,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "Aşağıdaki `range()` ifadesinin çıktısı nedir?"
        else
            "What is the output of the following `range()` expression?",
        codeSnippet = if (language == "TR")
            """
            list(range(2, 8, 3))
            """.trimIndent()
        else
            """
            list(range(2, 8, 3))
            """.trimIndent(),
        options = if (language == "TR")
            listOf(
                "[2, 5]",
                "[2, 3, 5, 8]",
                "[2, 8]",
                "[3, 6]"
            )
        else
            listOf(
                "[2, 5]",
                "[2, 3, 5, 8]",
                "[2, 8]",
                "[3, 6]"
            ),
        correctAnswerIndex = 0,
        solution = if (language == "TR")
            "`range(2, 8, 3)` 2'den başlar, 8'e kadar 3'er artar. 2, 5 değerlerini üretir."
        else
            "`range(2, 8, 3)` starts at 2, goes up to 8 with steps of 3, producing 2 and 5.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 2,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    CodeOutputQuestion(
        question = if (language == "TR")
            "Bu kodun çıktısı nedir?"
        else
            "What is the output of this code?",
        codeSnippet = """
            for i in range(3):
                print(i * 2)
        """.trimIndent(),
        solution = if (language == "TR")
            "0\n2\n4"
        else
            "0\n2\n4",
        isVisible = counter == metinListesi.size + 3,
        onAnswerChange = {},
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    CodeOutputQuestion(
        question = if (language == "TR")
            "Bu kod ekrana ne yazdırır?"
        else
            "What does this code print?",
        codeSnippet = if (language == "TR") """
            toplam = 0
            for i in range(1, 5):
                toplam = toplam + i
            print(toplam)
        """.trimIndent() else """
            total = 0
            for i in range(1, 5):
                total = total + i
            print(total)
        """.trimIndent(),
        solution = if (language == "TR")
            "10"
        else
            "10",
        isVisible = counter == metinListesi.size + 4,
        onAnswerChange = {},
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    //---------------------------------------------------------------------------- End Message

    val closingText = when (language) {
        "TR" -> "Harika! Artık `for` döngüsünü ve `range()` fonksiyonunu öğrendin. Şartların ve aralıkların nasıl değiştiğine dikkat etmeyi unutma!"
        "EN" -> "Awesome! You now understand `for` loops and the `range()` function. Don’t forget to pay attention to how conditions and ranges change!"
        else -> ""
    }

    konusmaBalonu(closingText,  maskotKobot, counter == metinListesi.size + 5)

    //---------------------------------------------------------------------------- Progress Bar

    progressBar(
        metinListesi.size + 5,
        counter,
        navController,
        Screen.lvl15.createRoute(language),
        context,
        level,
        language
    )
}
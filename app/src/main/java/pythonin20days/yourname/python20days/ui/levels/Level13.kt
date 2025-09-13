package com.example.dersapp.levels

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.yourname.python20days.R
import com.example.dersapp.components.*
import com.example.dersapp.data.Screen

@Composable
fun Level13(navController: NavController, language: String) {

    //---------------------------------------------------------------------------- Variables

    val backGroundImage = painterResource(id = R.drawable.bolumarkaplani)
    val maskotKobot = R.drawable.maskotbook
    var metinIndex by remember { mutableStateOf(0) }
    var counter by remember { mutableStateOf(1) }
    val context = LocalContext.current
    val level = 13

    //---------------------------------------------------------------------------- Texts

    val metinListesi = when (language) {
        "TR" -> listOf(
            "Bugünkü konumuz: Python'da `while` döngüsü.",
            "`while`, bir koşul doğru olduğu sürece kodu tekrar tekrar çalıştırır.",
            "Yani: 'şart doğruysa devam et' mantığıyla çalışır.",
            "Şimdi satır satır inceleyeceğimiz bir örneğe bakalım:",
            "1️⃣ `number = 1` \n→ Sayacı başlatıyoruz. number değişkenini 1 olarak belirledik.",
            "2️⃣ `while number <= 3:` \n→ number 3'e eşit veya küçük olduğu sürece döngü devam edecek.",
            "3️⃣ `print(\"Sayı:\", number)` \n→ Ekrana mevcut sayıyı yazdırır.",
            "4️⃣ `number += 1` \n→ Sayacı 1 artırır. Böylece döngü ilerler.",
            "Bu işlemler sırayla şu şekilde çalışır:",
            "→ number = 1\n→ koşul doğru\n→ yazdır \n→ number 2 olur.",
            "→ number = 2\n→ koşul doğru\n→ yazdır \n→ number 3 olur.",
            "→ number = 3\n→ koşul doğru\n→ yazdır \n→ number 4 olur.",
            "→ number = 4\n→ koşul artık yanlış\n→ döngü biter.",
            "Yani çıktımız: Sayı: 1, Sayı: 2, Sayı: 3",
            "Eğer while döngüsünün içindeki koşulu durduracak bir durum oluşmazsa, döngü sonsuza kadar çalışır.",
            "Şimdi seninle birlikte bazı sorulara bakalım."
        )
        "EN" -> listOf(
            "Today’s topic: `while` loop in Python.",
            "`while` keeps running the code **as long as** the condition is True.",
            "In short: 'keep going if the condition is true'.",
            "Now let's analyze an example step-by-step:",
            "1️⃣ `number = 1` \n→ We start the counter with 1.",
            "2️⃣ `while number <= 3:` \n→ As long as number is less than or equal to 3, the loop continues.",
            "3️⃣ `print(\"Number:\", number)` \n→ Prints the current number.",
            "4️⃣ `number += 1` \n→ Increases the counter by 1 to move forward.",
            "Here’s how the loop runs step by step:",
            "→ number = 1\n→ condition is True\n→ print\n→ number becomes 2",
            "→ number = 2\n→ condition is True\n→ print\n→ number becomes 3",
            "→ number = 3\n→ condition is True\n→ print\n→ number becomes 4",
            "→ number = 4\n→ condition is False\n→ loop ends",
            "So the output is: Number: 1, Number: 2, Number: 3",
            "If there is nothing to stop the condition inside the while loop, the loop will run forever.",
            "Now let's look at some questions together."
        )
        else -> emptyList()
    }

    //---------------------------------------------------------------------------- Click Control

    ClickablePlace(
        counter,
        metinListesi.size + 7,
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

    val whileCode = """
        number = 1
        while number <= 3:
            print("Sayı:", number)
            number = number + 1
        
        -> Sayı: 1 <-
        -> Sayı: 2 <-
        -> Sayı: 3 <-
    """.trimIndent()

    ShowCode(whileCode, isVisible = metinIndex in 4..13)

    //---------------------------------------------------------------------------- Question
    MultipleChoiceQuestion(
        question = if (language == "TR")
            "Aşağıdaki kod çalıştırıldığında ne olur?"
        else
            "What happens when the following code is executed?",
        codeSnippet = if (language == "TR")
            """
            sayi = 1
            while sayi < 5:
                print("Merhaba")""".trimIndent()
            else
            """
            number = 1
            while number < 5:
                print("Hello") """.trimIndent(),
        options = if (language == "TR")
            listOf(
                "Ekrana yalnızca bir kez 'Merhaba' yazdırır",
                "Ekrana beş kez 'Merhaba' yazdırır",
                "Sonsuz döngüye girer ve sürekli 'Merhaba' yazdırır",
                "Hata verir çünkü koşul yanlış"
            )
        else
            listOf(
                "Prints 'Hello' only once",
                "Prints 'Hello' five times",
                "Enters an infinite loop and keeps printing 'Hello'",
                "Gives an error because the condition is false"
            ),
        correctAnswerIndex = 2,
        solution = if (language == "TR")
            "Değişken olan 'sayi' döngü içinde hiçbir zaman artmadığı için, koşul hep doğru kalır ve döngü sonsuza kadar devam eder."
        else
            "The variable 'number' is never updated inside the loop, so the condition remains true and the loop runs infinitely.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 1,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "Aşağıdaki kodun çıktısı nedir?"
        else
            "What is the output of the following code?",
        codeSnippet = if (language == "TR") """
        i = 0
        while i < 3:
            if i % 2 == 0:
                print("Çift")
            else:
                print("Tek")
            i = i + 1
    """.trimIndent() else """
        i = 0
        while i < 3:
            if i % 2 == 0:
                print("Even")
            else:
                print("Odd")
            i = i + 1
    """.trimIndent(),
        options = if (language == "TR")
            listOf(
                "Çift Tek Çift",
                "Çift Çift Çift",
                "Çift Tek Tek",
                "Çift Tek"
            )
        else
            listOf(
                "Even Odd Even",
                "Even Even Even",
                "Even Odd Odd",
                "Even Odd"
            ),
        correctAnswerIndex = 0,
        solution = if (language == "TR")
            "i değişkeni 0'dan başlayarak 3'ten küçük olduğu sürece döngü çalışır. i sırasıyla 0, 1, 2 olur. 0 ve 2 çift, 1 tektir. Ancak çıktı arada boşluk bırakmadan ayrı satırlarda gelir: 'ÇiftTekÇift'."
        else
            "The variable i goes from 0 to 2. 0 and 2 are even, 1 is odd. So the output is 'EvenOddEven' printed without spaces.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 2,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    CodeOutputQuestion(
        question = if (language == "TR") "Bu kodun çıktısı nedir?" else "What is the output of this code?",
        codeSnippet = """
            i = 0
            while i < 4:
                print(i)
                i = i + 1
        """.trimIndent(),
        solution = "0\n1\n2\n3",
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
        i = 1
        while i <= 5:
            if i % 2 == 0:
                print("Çift")
            else:
                print("Tek")
            i = i + 1
    """.trimIndent()else """
        i = 1
        while i <= 5:
            if i % 2 == 0:
                print("Even")
            else:
                print("Odd")
            i = i + 1
    """.trimIndent(),
        solution = if (language == "TR")
            "Tek\nÇift\nTek\nÇift\nTek"
        else
            "Odd\nEven\nOdd\nEven\nOdd",
        isVisible = counter == metinListesi.size + 4,
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
        x = 0
        while x < 4:
            if x != 2 and x < 3:
                print("Devam")
            else:
                print("Dur")
            x = x + 1
    """ else """
        x = 0
        while x < 4:
            if x != 2 and x < 3:
                print("Continue")
            else:
                print("Stop")
            x = x + 1
    """.trimIndent(),
        solution = if (language == "TR")
            "Devam\nDevam\nDur\nDur"
        else
            "Continue\nContinue\nStop\nStop",
        isVisible = counter == metinListesi.size + 5,
        onAnswerChange = {},
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    FillInTheBlankQuestion(
        question = if (language == "TR")
            "Aşağıdaki boşlukları, kodun mantığına ve çıktısına göre doldurun:"
        else
            "Fill in the blanks based on the logic and output of the code below:",
        codeSnippet ="""
        x = 0
        while x < 4:
            if x (1.____) 2 == 0 :
                print(x)
            x = x + 1
            
        -> 0 <-
        -> 2 <-
        """.trimIndent(),
        correctAnswers = listOf("%"),
        answerCount = 1,
        isVisible = counter == metinListesi.size + 6,
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )
        //---------------------------------------------------------------------------- End Message

    val closingText = when (language) {
        "TR" -> "Süper! Artık while döngüsünü ve nasıl çalıştığını öğrendin. Kodun içinde şartlar nasıl değişiyor dikkat etmeyi unutma!"
        "EN" -> "Great! Now you understand how `while` loops work. Always pay attention to changing conditions!"
        else -> ""
    }

    konusmaBalonu(closingText, maskotKobot, counter == metinListesi.size + 7)

    //---------------------------------------------------------------------------- Progress Bar

    progressBar(
        metinListesi.size + 7,
        counter,
        navController,
        Screen.lvl14.createRoute(language),
        context,
        level,
        language
    )
}

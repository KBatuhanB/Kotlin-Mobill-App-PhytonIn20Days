package com.example.dersapp.levels

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.yourname.python20days.R
import com.example.dersapp.components.*
import com.example.dersapp.data.Screen

@Composable
fun Level16(navController: NavController, language: String) {

    //---------------------------------------------------------------------------- Variables

    val backGroundImage = painterResource(id = R.drawable.bolumarkaplani)
    val maskotKobot = R.drawable.maskothi
    var metinIndex by remember { mutableStateOf(0) }
    var counter by remember { mutableStateOf(1) }
    val context = LocalContext.current
    val level = 16

    //---------------------------------------------------------------------------- Texts

    val metinListesi = when (language) {
        "TR" -> listOf(
            "Bugün Python'da listelerde döngü kurmayı öğreneceğiz!",
            "`for` döngüsü, listedeki her eleman üzerinde sırayla işlem yapmamıza olanak sağlar.",
            "Örneğin:\n`for meyve in meyveler:`\n    `print(meyve)`",
            "Bu kod, listedeki her meyveyi ekrana yazdırır.",
            "`len()` fonksiyonu liste uzunluğunu verir. Döngü içinde indeksle işlem yaparken kullanışlıdır.",
            "Örneğin:\n`for i in range(len(meyveler)):`\n    `print(meyveler[i])`",
            "Bu şekilde indeksleri de kontrol ederek işlem yapabiliriz.",
            "`for` döngüsü, liste elemanlarını tek tek almak için en yaygın yöntemlerden biridir.",
            "Haydi örneklerle pratik yapalım!"
        )
        "EN" -> listOf(
            "Today we’ll learn how to loop through lists in Python!",
            "The `for` loop lets us process each item in a list one by one.",
            "Example:\n`for fruit in fruits:`\n    `print(fruit)`",
            "This code prints each fruit in the list.",
            "The `len()` function gives the length of a list. It's useful when looping with indexes.",
            "Example:\n`for i in range(len(fruits)):`\n    `print(fruits[i])`",
            "This way, we can access both index and value.",
            "`for` loop is one of the most common ways to iterate through a list.",
            "Let’s do some practice with examples!"
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

    val loopExample1 = """
        meyveler = ["elma", "muz", "çilek"]
        for meyve in meyveler:
            print(meyve)

        -> elma
        -> muz
        -> çilek
    """.trimIndent()
    ShowCode(loopExample1, isVisible = metinIndex in 2..3)

    val loopExample2 = """
        sayilar = [10, 20, 30]
        for i in range(len(sayilar)):
            print(sayilar[i])

        -> 10
        -> 20
        -> 30
    """.trimIndent()
    ShowCode(loopExample2, isVisible = metinIndex in 4..6)

    //---------------------------------------------------------------------------- Questions

    CodeOutputQuestion(
        question = if (language == "TR")
            "Bu kod ekrana ne yazdırır?"
        else
            "What does this code print?",
        codeSnippet = if (language == "TR") """
        sayilar = [2, 4, 6]
        for i in sayilar:
            print(i)
    """.trimIndent() else """
        numbers = [2, 4, 6]
        for i in numbers:
            print(i)
    """.trimIndent(),
        solution = "2\n4\n6",
        isVisible = counter == metinListesi.size + 1,
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
        isimler = ["Ali", "Ayşe", "Mehmet"]
        print(len(isimler))
    """.trimIndent() else """
        names = ["Ali", "Ayşe", "Mehmet"]
        print(len(names))
    """.trimIndent(),
        solution = "3",
        isVisible = counter == metinListesi.size + 2,
        onAnswerChange = {},
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )


    MultipleChoiceQuestion(
        question = if (language == "TR")
            "`for` döngüsü ile ilgili hangisi doğrudur?"
        else
            "Which of the following is true about `for` loop?",
        options = if (language == "TR")
            listOf("Sadece bir kez çalışır", "Tüm listeyi tek seferde yazdırır", "Liste elemanları üzerinde tek tek döner", "Sadece indekslerle çalışır")
        else
            listOf("Runs only once", "Prints the whole list at once", "Loops through list items one by one", "Only works with indexes"),
        correctAnswerIndex = 2,
        solution = if (language == "TR")
            "`for` döngüsü listedeki elemanlar üzerinde sırayla işlem yapar."
        else
            "`for` loop iterates over the items in the list one by one.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 3,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "`range(len(liste))` ifadesi ne işe yarar?"
        else
            "What does `range(len(list))` do?",
        codeSnippet = """
            range(len(liste))
        """.trimIndent(),
        options = if (language == "TR")
            listOf("Listedeki elemanları toplar", "Listenin uzunluğunu döndürür", "Listedeki indekslerde döngü kurar", "Hiçbir işe yaramaz")
        else
            listOf("Adds list elements", "Returns the length of list", "Loops through list indexes", "Does nothing"),
        correctAnswerIndex = 2,
        solution = if (language == "TR")
            "`range(len(liste))` ifadesi, 0'dan listenin uzunluğuna kadar bir döngü başlatır."
        else
            "`range(len(list))` starts a loop from 0 to the length of the list.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 4,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )
    //---------------------------------------------------------------------------- End Message

    val closingText = when (language) {
        "TR" -> "Tebrikler! Artık listeler üzerinde `for` döngüsüyle nasıl gezileceğini ve `len()` fonksiyonu ile liste uzunluğunu nasıl kullanacağını biliyorsun."
        "EN" -> "Congratulations! Now you know how to use a `for` loop to iterate through lists and how to use the `len()` function to get their length."
        else -> ""
    }

    konusmaBalonu(closingText, maskotKobot, counter == metinListesi.size + 5)

    //---------------------------------------------------------------------------- Progress Bar

    progressBar(
        metinListesi.size + 5,
        counter,
        navController,
        Screen.lvl17.createRoute(language),
        context,
        level,
        language
    )
}
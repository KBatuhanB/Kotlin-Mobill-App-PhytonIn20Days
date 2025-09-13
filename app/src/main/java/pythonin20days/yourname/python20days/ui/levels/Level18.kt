package com.example.dersapp.levels

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.yourname.python20days.R
import com.example.dersapp.components.*
import com.example.dersapp.data.Screen

@Composable
fun Level18(navController: NavController, language: String) {

    //---------------------------------------------------------------------------- Variables

    val backGroundImage = painterResource(id = R.drawable.bolumarkaplani)
    val maskotKobot = R.drawable.maskotbook
    var metinIndex by remember { mutableStateOf(0) }
    var counter by remember { mutableStateOf(1) }
    val context = LocalContext.current
    val level = 18

    //---------------------------------------------------------------------------- Texts

    val metinListesi = when (language) {
        "TR" -> listOf(
            "Bugünkü konumuz: Python'da hata yönetimi.",
            "Programlarımız bazen kullanıcıdan gelen hatalı girişler ya da beklenmeyen durumlar yüzünden hata verebilir.",
            "Eğer bu hataları önceden öngörüp yakalayamazsak, program çalışmayı durdurur (crash olur).",
            "İşte burada `try` ve `except` blokları devreye girer.",
            "`try` bloğunda hata oluşabilecek kodlar yazılır.",
            "`except` bloğu ise hata olursa çalışacak kodları içerir.",
            "Bu sayede kullanıcı hatalı giriş yapsa bile program çökmek yerine uygun bir mesaj gösterebilir.",
            "Basit bir örnekle açıklayalım:",
            "`sayi = int(input(\"Bir sayı girin: \"))`\n→ Kullanıcı bir sayı girmeli.",
            "Ama kullanıcı metin girerse `int()` fonksiyonu hata verir.",
            "Bu durumda program çöker. Ama `try/except` ile bu hatayı yakalayabiliriz.",
            "Aşağıdaki örneği inceleyelim:",
            "Kullanıcıdan sayı almaya çalışırız ve eğer hata olursa uyarı veririz.",
            "`try` içinde hata oluşursa hemen `except` bloğu çalışır.",
            "`except` bloğuna istenirse hata türü de eklenebilir (`except ValueError:` gibi).",
            "Artık hata alsak bile program çökmeden çalışmaya devam eder.",
            "Şimdi birlikte bazı sorular çözelim."
        )
        "EN" -> listOf(
            "Today's topic: Error handling in Python.",
            "Sometimes our programs may crash due to incorrect user input or unexpected situations.",
            "If we don't predict and catch these errors, the program stops running (crashes).",
            "That's where `try` and `except` blocks come in.",
            "The `try` block contains the code that might cause an error.",
            "The `except` block contains what to do if an error occurs.",
            "So, even if the user enters wrong input, the program can show a message instead of crashing.",
            "Let’s explain with a simple example:",
            "`sayi = int(input(\"Enter a number: \"))`\n→ The user must enter a number.",
            "But if the user enters text, `int()` will throw an error.",
            "In that case, the program crashes. But with `try/except`, we can catch the error.",
            "Let’s look at this example:",
            "We try to get input from the user and show a warning if there’s an error.",
            "If an error happens in `try`, it immediately jumps to the `except` block.",
            "You can also specify the error type in `except` (like `except ValueError:`).",
            "Now, even if there’s an error, the program continues running.",
            "Now let’s try a few questions together."
        )
        else -> emptyList()
    }

    //---------------------------------------------------------------------------- Click Control

    ClickablePlace(
        counter,
        metinListesi.size + 6,
        metinIndex,
        { counter = it },
        { if (counter <= metinListesi.size) metinIndex = it }
    )

    //---------------------------------------------------------------------------- Background

    lvlBackground(backGroundImage, navController)

    //---------------------------------------------------------------------------- Speech Bubble

    val balonMetni = if (metinIndex in metinListesi.indices) metinListesi[metinIndex] else ""

    konusmaBalonu(balonMetni, maskotKobot, counter <= metinListesi.size)

    //---------------------------------------------------------------------------- Code Block

    val codeExample1 = if (language == "EN") {
        """
        try:
            number = int(input("Enter a number: "))
            print("You entered:", number)
        except:
            print("Invalid input!")
        """.trimIndent()
    } else {
        """
        try:
            sayi = int(input("Bir sayı girin: "))
            print("Girdiğiniz sayı:", sayi)
        except:
            print("Geçersiz giriş!")
        """.trimIndent()
    }

    ShowCode(codeExample1, isVisible = metinIndex in 11..14)

    //---------------------------------------------------------------------------- Questions

    CodeOutputQuestion(
        question = if (language == "TR") "Bu kod ne yazdırır?" else "What does this code print?",
        codeSnippet = if (language == "TR") """
            try:
                x = int("abc")
            except:
                print("Hata oluştu!")
        """.trimIndent() else """
            try:
                x = int("abc")
            except:
                print("An error occurred!")
        """.trimIndent(),
        solution = if (language == "TR") "Hata oluştu!" else "An error occurred!",
        isVisible = counter == metinListesi.size + 1,
        onAnswerChange = {},
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    FillInTheBlankQuestion(
        question = if (language == "TR") "Eksik olan anahtar kelimeyi yazın:" else "Write the missing keyword:",
        codeSnippet = if (language == "TR") """
            (1.____):
                x = int(input("Sayı: "))
        """.trimIndent() else """
            (1.____):
                x = int(input("Number: "))
        """.trimIndent(),
        correctAnswers = listOf("try"),
        answerCount = 1,
        isVisible = counter == metinListesi.size + 2,
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    FillInTheBlankQuestion(
        question = if (language == "TR") "Eksik olan anahtar kelimeyi yazın:" else "Write the missing keyword:",
        codeSnippet = if (language == "TR") """
            try:
                x = int("abc")
            (1.____):
                print("Hata!")
        """.trimIndent() else """
            try:
                x = int("abc")
            (1.____):
                print("Error!")
        """.trimIndent(),
        correctAnswers = listOf("except"),
        answerCount = 1,
        isVisible = counter == metinListesi.size + 3,
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "Aşağıdakilerden hangisi `try/except` yapısının doğru kullanımını gösterir?"
        else
            "Which one shows the correct usage of `try/except`?",
        codeSnippet = "",
        options = if (language == "TR")
            listOf(
                "`try: print(\"Merhaba\")`",
                "`try print(\"Merhaba\") except:`",
                "`try:\n    print(\"Merhaba\")\nexcept:\n    print(\"Hata\")`",
                "`except:\n    print(\"Hata\")`"
            )
        else
            listOf(
                "`try: print(\"Hello\")`",
                "`try print(\"Hello\") except:`",
                "`try:\n    print(\"Hello\")\nexcept:\n    print(\"Error\")`",
                "`except:\n    print(\"Error\")`"
            ),
        correctAnswerIndex = 2,
        solution = if (language == "TR")
            "`try:` ve `except:` blokları birlikte kullanılmalı ve içlerine girintiyle kod yazılmalıdır."
        else
            "`try:` and `except:` must be used together with properly indented code.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 4,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "Hangi durumda `except ValueError:` kullanılmalıdır?"
        else
            "When should `except ValueError:` be used?",
        codeSnippet = "",
        options = if (language == "TR")
            listOf(
                "Bir dosya bulunamadığında",
                "Kullanıcı metin yerine sayı girdiğinde",
                "Kullanıcı sayı yerine metin girdiğinde",
                "Kodda yazım hatası varsa"
            )
        else
            listOf(
                "When a file is not found",
                "When user enters a number instead of text",
                "When user enters text instead of a number",
                "When there's a syntax error"
            ),
        correctAnswerIndex = 2,
        solution = if (language == "TR")
            "Kullanıcı sayı yerine metin girerse `int()` fonksiyonu `ValueError` hatası verir."
        else
            "`ValueError` occurs when user enters text instead of a number.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 5,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    //---------------------------------------------------------------------------- End Message

    val closingText = when (language) {
        "TR" -> "Harika! Artık try/except ile hataları nasıl yakalayacağını ve kullanıcıdan gelen hatalara karşı programını nasıl koruyacağını biliyorsun!"
        "EN" -> "Great! Now you know how to catch errors using try/except and protect your program from user mistakes!"
        else -> ""
    }

    konusmaBalonu(closingText, maskotKobot, counter == metinListesi.size + 6)

    //---------------------------------------------------------------------------- Progress Bar

    progressBar(
        metinListesi.size + 6,
        counter,
        navController,
        Screen.lvl19.createRoute(language),
        context,
        level,
        language
    )
}

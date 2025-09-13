package com.example.dersapp.levels

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.yourname.python20days.R
import com.example.dersapp.components.*
import com.example.dersapp.data.Screen

@Composable
fun Level17(navController: NavController, language: String) {

    //---------------------------------------------------------------------------- Variables

    val backGroundImage = painterResource(id = R.drawable.bolumarkaplani)
    val maskotKobot = R.drawable.maskotornegin
    var metinIndex by remember { mutableStateOf(0) }
    var counter by remember { mutableStateOf(1) }
    val context = LocalContext.current
    val level = 17

    //---------------------------------------------------------------------------- Texts

    val metinListesi = when (language) {
        "TR" -> listOf(
            "Bugünkü konumuz: Python'da fonksiyonlara giriş.",
            "`def` anahtar kelimesi ile fonksiyon tanımlanır.",
            "Fonksiyonlar, bir işi gerçekleştirmek için yazılmış kod bloklarıdır.",
            "Fonksiyonları tekrar tekrar çağırabiliriz.",
            "Örnek bir fonksiyon tanımı:",
            "`def selamla():` \n→ Fonksiyon tanımı başladı.",
            "`print(\"Merhaba!\")` \n→ Fonksiyon çalıştığında yapılacak işlem.",
            "Bu fonksiyonu çağırmak için sadece ismini yazarız: `selamla()`",
            "Şimdi parametreli bir örneğe bakalım:",
            "`def topla(a, b):` \n→ İki parametre alır.",
            "`return a + b` \n→ Sonucu döndürür.",
            "`print(topla(3, 4))` \n→ Ekrana 7 yazdırır.",
            "Yani fonksiyonlar parametre alabilir ve değer döndürebilir.",
            "`return` anahtar kelimesi bir değeri geri döndürmek için kullanılır.",
            "Fonksiyon çağrıldığında, içinde tanımlı kodlar çalışır.",
            "Şimdi birlikte bazı soruları çözelim."
        )
        "EN" -> listOf(
            "Today's topic: Introduction to functions in Python.",
            "Functions are defined using the `def` keyword.",
            "Functions are blocks of code written to perform a task.",
            "We can call them multiple times.",
            "Here’s an example of a simple function:",
            "`def greet():` \n→ Starts the function definition.",
            "`print(\"Hello!\")` \n→ This runs when the function is called.",
            "To call the function, we just write its name: `greet()`",
            "Now let's look at one with parameters:",
            "`def add(a, b):` \n→ Takes two parameters.",
            "`return a + b` \n→ Returns the result.",
            "`print(add(3, 4))` \n→ Outputs 7.",
            "So functions can take parameters and return values.",
            "`return` is used to give back a value.",
            "When called, the function executes the code inside.",
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
    // Kod 1: Fonksiyon tanımı başlığı
    val exampleCode1 = if (language == "EN") {
        """
    def greet():
    """.trimIndent()
    } else {
        """
    def selamla():
    """.trimIndent()
    }
    ShowCode(exampleCode1, isVisible = metinIndex == 5)


// Kod 2: Fonksiyonun içine yazdırma eklenmiş
    val exampleCode2 = if (language == "EN") {
        """
    def greet():
        print("Hello!")
    """.trimIndent()
    } else {
        """
    def selamla():
        print("Merhaba!")
    """.trimIndent()
    }
    ShowCode(exampleCode2, isVisible = metinIndex == 6)


// Kod 3: Fonksiyon çağrılmış
    val exampleCode3 = if (language == "EN") {
        """
    def greet():
        print("Hello!")
        
    greet()
    -> Hello <-
    """.trimIndent()
    } else {
        """
    def selamla():
        print("Merhaba!")
        
    selamla()
    -> Merhaba <-
    """.trimIndent()
    }
    ShowCode(exampleCode3, isVisible = metinIndex == 7)


// Kod 4: Parametreli fonksiyon örneği
    val exampleCode4 = if (language == "EN") {
        """
    def add(a, b):
        return a + b

    print(add(3, 5))
    -> 8 <-
    """.trimIndent()
    } else {
        """
    def topla(a, b):
        return a + b

    print(topla(3, 5))
    -> 8 <-
    """.trimIndent()
    }
    ShowCode(exampleCode4, isVisible = metinIndex in 9..14)

    //---------------------------------------------------------------------------- Questions

    CodeOutputQuestion(
        question = if (language == "TR") "Bu kodun çıktısı nedir?" else "What is the output of this code?",
        codeSnippet = """
        def mesaj():
            return "Merhaba"

        print(mesaj())
        """.trimIndent(),
        solution = "Merhaba",
        isVisible = counter == metinListesi.size + 1,
        onAnswerChange = {},
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    CodeOutputQuestion(
        question = if (language == "TR") "Aşağıdaki kod ne yazdırır?" else "What does this code print?",
        codeSnippet = """
        def carp(a, b):
            return a * b

        print(carp(2, 4))
        """.trimIndent(),
        solution = "8",
        isVisible = counter == metinListesi.size + 2,
        onAnswerChange = {},
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "Aşağıdakilerden hangisi bir fonksiyon tanımıdır?"
        else
            "Which of the following defines a function?",
        codeSnippet = "",
        options = if (language == "TR")
            listOf(
                "`print(\"Merhaba\")`",
                "`def selamla():`",
                "`selamla()`",
                "`a = 5`"
            )
        else
            listOf(
                "`print(\"Hello\")`",
                "`def greet():`",
                "`greet()`",
                "`x = 10`"
            ),
        correctAnswerIndex = 1,
        solution = if (language == "TR")
            "`def selamla():` bir fonksiyon tanımıdır. Diğerleri fonksiyon çağırma ya da değişken atamasıdır."
        else
            "`def greet():` is a function definition. Others are calls or assignments.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 3,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "Bir fonksiyonun değer döndürmesi için hangi anahtar kelime kullanılır?"
        else
            "Which keyword is used for a function to return a value?",
        codeSnippet = "",
        options = listOf("get", "return", "print", "yield"),
        correctAnswerIndex = 1,
        solution = if (language == "TR")
            "`return` anahtar kelimesi fonksiyonun dışına değer göndermek için kullanılır."
        else
            "`return` is used to send a value out of a function.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 4,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    FillInTheBlankQuestion(
        question = if (language == "TR")
            "Eksik olan anahtar kelimeyi yazın:"
        else
            "Write the missing keyword:",
        codeSnippet = if (language == "TR")
            """
            def kare(x):
                (1.____) x * x
            """.trimIndent()
        else
            """
            def square(x):
                (1.____) x * x
            """.trimIndent(),
        correctAnswers = listOf("return"),
        answerCount = 1,
        isVisible = counter == metinListesi.size + 5,
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    //---------------------------------------------------------------------------- End Message

    val closingText = when (language) {
        "TR" -> "Harika! Artık Python'da fonksiyonları tanımlamayı, parametre kullanmayı ve dönüş değerlerini biliyorsun!"
        "EN" -> "Great! Now you know how to define functions in Python, use parameters, and return values!"
        else -> ""
    }

    konusmaBalonu(closingText,  maskotKobot, counter == metinListesi.size + 6)

    //---------------------------------------------------------------------------- Progress Bar

    progressBar(
        metinListesi.size + 6,
        counter,
        navController,
        Screen.lvl18.createRoute(language),
        context,
        level,
        language
    )
}

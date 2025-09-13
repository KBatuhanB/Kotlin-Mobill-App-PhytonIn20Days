package com.example.dersapp.levels

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
import com.example.dersapp.components.MultipleChoiceQuestion
import com.example.dersapp.components.konusmaBalonu
import com.example.dersapp.components.lvlBackground
import com.example.dersapp.components.ShowCode
import com.example.dersapp.components.progressBar
import com.example.dersapp.data.Screen

@Composable
fun Level2(navController: NavController, language: String) {
    val backGroundImage = painterResource(id = R.drawable.bolumarkaplani)
    val maskotKobot = R.drawable.maskotornegin
    var metinIndex by remember { mutableStateOf(0) }
    var counter by remember { mutableStateOf(1) }
    val context = LocalContext.current
    val level = 2

    val metinListesi = when (language) {
        "TR" -> listOf(
            "Bugün Python'da ilk kodumuzu yazacağız!",
            "print() komutu, ekrana bir şey yazdırmak için kullanılır.",
            "Mesela print(\"Merhaba!\") yazarsan, ekran şöyle görünür:",
            "Kod çıktısı aşağıda olduğu gibi kodun ekranda yazdığı şeyi sana gösteriyor.",
            "Kodları daha açıklayıcı hale getirmek için yorum satırlarını kullanırız.",
            "# işareti ile başlayan satırlar Python tarafından çalıştırılmaz.",
            "Bu satırlar sadece açıklama içindir ve kodu okuyana yardımcı olur.",
            "Haydi şimdi bir neler öğrendiğimize bakalım:"
        )
        "EN" -> listOf(
            "Today, we'll write our first code in Python!",
            "The print() command is used to display something on the screen.",
            "For example, if you write print(\"Hello!\"), the screen will look like this:",
            "The code output shows you what the code prints on the screen, as shown below.",
            "We use comments to make the code more descriptive.",
            "Lines starting with # are not executed by Python.",
            "These lines are only for explanation and help the reader understand the code.",
            "Let's see what we've learned so far:"
        )
        else -> emptyList()
    }

    ClickablePlace(
        counter,
        metinListesi.size + 2,
        metinIndex,
        { counter = it },
        { if (counter <= metinListesi.size) metinIndex = it })

    lvlBackground(backGroundImage, navController)

    val balonMetni = if (metinIndex in metinListesi.indices) metinListesi[metinIndex] else ""
    konusmaBalonu(balonMetni,  maskotKobot, counter <= metinListesi.size)

    val codeExample = when (language) {
        "TR" -> "print(\"Merhaba!\")\n-> Çıktı: Merhaba! <-"
        "EN" -> "print(\"Hello!\")\n-> Output: Hello! <-"
        else -> ""
    }
    ShowCode(code = codeExample, isVisible = metinIndex in 2..3)

    val codeExample2 = when (language) {
        "TR" -> "# Bu bir yorum satırıdır\nprint(\"Merhaba!\")\n-> Çıktı: Merhaba! <-"
        "EN" -> "# This is a comment\nprint(\"Hello!\")\n-> Output: Hello! <-"
        else -> ""
    }
    ShowCode(code = codeExample2, isVisible = metinIndex in 4..6)

    MultipleChoiceQuestion(
        question = when (language) {
            "TR" -> "Aşağıdaki kod çalıştırıldığında ekranda ne görünür?"
            "EN" -> "What appears on the screen when the following code runs?"
            else -> ""
        },
        codeSnippet = when (language) {
            "TR" -> "print(\"Merhaba Dünya\")"
            "EN" -> "print(\"Hello World!\")"
            else -> ""
        },
        options = when (language) {
            "TR" -> listOf("Merhaba", "print", "Merhaba Dünya", "\"Merhaba Dünya\"")
            "EN" -> listOf("Hello", "print", "Hello World", "\"Hello World\"")
            else -> emptyList()
        },
        correctAnswerIndex = 2,
        solution = when (language) {
            "TR" -> "print() komutu içindeki yazıyı aynen ekrana yazdırır. Sonuç: Merhaba Dünya"
            "EN" -> "The print() command displays the text inside it exactly as written. Result: Hello World"
            else -> ""
        },
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 1,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    MultipleChoiceQuestion(
        question = when (language) {
            "TR" -> "Aşağıdakilerden hangisi Python'da yorum (comment) satırıdır?"
            "EN" -> "Which of the following is a comment in Python?"
            else -> ""
        },
        options = when (language) {
            "TR" -> listOf("print(\"Merhaba\")", "# Bu bir yorum", "\"Yorum\"", "print(#Yorum)")
            "EN" -> listOf("print(\"Hello\")", "# This is a comment", "\"Comment\"", "print(#Comment)")
            else -> emptyList()
        },
        correctAnswerIndex = 1,
        solution = when (language) {
            "TR" -> "# ile başlayan satırlar yorum satırıdır. Python bu satırları çalıştırmaz."
            "EN" -> "Lines starting with # are comments. Python does not execute these lines."
            else -> ""
        },
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 2,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    konusmaBalonu(
        when (language) {
            "TR" -> "Tebrikler! Print fonksiyonunu ve yorum satırlarını öğrendin. Sonraki derse geçmek için devam et."
            "EN" -> "Congratulations! You've learned about the print function and comments. Continue to the next lesson."
            else -> ""
        },  maskotKobot, counter == metinListesi.size + 3
    )
    progressBar(
        metinListesi.size+3,
        counter,
        navController,
        Screen.lvl3.createRoute(language),
        context,
        level,
        language
    )
}
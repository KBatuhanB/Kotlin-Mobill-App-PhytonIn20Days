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
import com.example.dersapp.components.CodeOutputQuestion
import com.example.dersapp.components.MultipleChoiceQuestion
import com.example.dersapp.components.konusmaBalonu
import com.example.dersapp.components.lvlBackground
import com.example.dersapp.components.ShowCode
import com.example.dersapp.components.progressBar
import com.example.dersapp.data.Screen

@Composable
fun Level3(navController: NavController, language: String) {
    val backGroundImage = painterResource(id = R.drawable.bolumarkaplani)
    val maskotKobot = R.drawable.maskotbook
    var metinIndex by remember { mutableStateOf(0) }
    var counter by remember { mutableStateOf(1) }
    val context = LocalContext.current
    val level = 3

    val metinListesi = when (language) {
        "TR" -> listOf(
            "Selam! Bugünkü dersimize hoş geldin. KOBOT'la birlikte Python kodlarının nasıl çalıştığını öğreneceğiz.",
            "Python'da kodlar yukarıdan aşağıya, satır satır çalışır.",
            "Bilgisayar her satırı sırayla okur ve çalıştırır. Önce birinci satır, sonra ikinci, sonra üçüncü...",
            "Şimdi aşağıdaki örneğe birlikte bakalım.",
            "Bu ilk satırda bir değişken tanımlıyoruz ve içine 5 sayısını koyuyoruz.",
            "Sonra başka bir değişken tanımlayıp içine 2 sayısını koyuyoruz.",
            "Bu satırda x ve y'yi toplayıp sonucu 'toplam' adlı değişkene yazıyoruz.",
            "Son olarak bu sonucu ekranda göstermek için print() fonksiyonunu kullanıyoruz.",
            "Kodları dikkatli incelediğinde, her satırın bir görev yaptığını göreceksin.",
            "Eğer bu satırların sırasını değiştirirsek, kod ya hata verir ya da farklı bir sonuç üretir.",
            "Tıpkı kahve yapmak gibi: Önce suyu ısıtırsın, sonra kahveyi eklersin. Eğer tersini yaparsan, işler karışır!",
            "İşte bu yüzden kod akışı çok önemlidir. Her şey adım adım ve sırayla olmalı.",
            "Haydi şimdi bir neler öğrendiğimize bakalım:"
        )
        "EN" -> listOf(
            "Hi! Welcome to today's lesson. With KOBOT, we'll learn how Python code runs.",
            "In Python, code runs from top to bottom, line by line.",
            "The computer reads and executes each line one by one. First the first line, then the second, then the third...",
            "Now let's look at the example below together.",
            "In this first line, we're defining a variable and assigning it the value 5.",
            "Then we define another variable and assign it the value 2.",
            "In this line, we're adding x and y and storing the result in a variable called 'total'.",
            "Finally, we use the print() function to display this result on the screen.",
            "When you examine the code carefully, you'll see that each line has a specific task.",
            "If we change the order of these lines, the code will either produce an error or a different result.",
            "It's just like making coffee: heat the water first, then add the coffee — otherwise, it gets messy.",
            "That's why code flow is so important. Everything should be step by step and in order.",
            "Now let's see what we've learned:"
        )
        else -> emptyList()
    }

    ClickablePlace(
        counter,
        metinListesi.size + 3,
        metinIndex,
        { counter = it },
        { if (counter <= metinListesi.size) metinIndex = it })

    lvlBackground(backGroundImage, navController)

    val balonMetni = if (metinIndex in metinListesi.indices) metinListesi[metinIndex] else ""
    konusmaBalonu(balonMetni, maskotKobot, counter <= metinListesi.size)

    val codeExample = when (language) {
        "TR" -> "x = 5\ny = 2\ntoplam = x + y\nprint(toplam)\n-> Çıktı: 7 <-"
        "EN" -> "x = 5\ny = 2\ntotal = x + y\nprint(total)\n-> Output: 7 <-"
        else -> ""
    }
    ShowCode(code = codeExample, isVisible = metinIndex in 3..9)

    CodeOutputQuestion(
        question = when (language) {
            "TR" -> "Aşağıdaki kodun çıktısı nedir?"
            "EN" -> "What is the output of the following code?"
            else -> ""
        },
        codeSnippet = "x = 4\ny = 1\nprint(x - y)",
        solution = "3",
        isVisible = counter == metinListesi.size + 1,
        onAnswerChange = { },
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    MultipleChoiceQuestion(
        question = when (language) {
            "TR" -> "Kodların sırasını değiştirirsek ne olur?"
            "EN" -> "What happens if we change the order of the code?"
            else -> ""
        },
        options = when (language) {
            "TR" -> listOf(
                "Hiçbir şey değişmez",
                "Kod daha hızlı çalışır",
                "Farklı bir sonuç çıkar veya hata verir",
                "Yalnızca çıktının görünüşü değişir"
            )
            "EN" -> listOf(
                "Nothing changes",
                "The code runs faster",
                "A different result appears or an error occurs",
                "Only the appearance of the output changes"
            )
            else -> emptyList()
        },
        correctAnswerIndex = 2,
        solution = when (language) {
            "TR" -> "Kodlar sırayla çalışır. Sıra değişirse, hata alabiliriz veya sonuç değişebilir."
            "EN" -> "Code runs in order. If the order changes, we might get an error or a different result."
            else -> ""
        },
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 2,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    konusmaBalonu(
        when (language) {
            "TR" -> "Harika! Artık kodların nasıl çalıştığını biliyorsun. Hadi bir sonraki derse geçelim!"
            "EN" -> "Great! Now you know how code works. Let's move on to the next lesson!"
            else -> ""
        },
        maskotKobot, counter == metinListesi.size + 3
    )
    progressBar(
        metinListesi.size+3,
        counter,
        navController,
        Screen.lvl4.createRoute(language),
        context,
        level,
        language
    )
}
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
import com.example.dersapp.components.FillInTheBlankQuestion
import com.example.dersapp.components.MultipleChoiceQuestion
import com.example.dersapp.data.Screen
import com.example.dersapp.components.konusmaBalonu
import com.example.dersapp.components.lvlBackground
import com.example.dersapp.components.ShowCode
import com.example.dersapp.components.progressBar


@Composable
fun Level4(navController: NavController, language: String) {

    //-----------------------------------------------------------------------------Varaibles

    val backGroundImage = painterResource(id = R.drawable.bolumarkaplani)
    val maskotKobot = R.drawable.maskotpc
    var metinIndex by remember { mutableStateOf(0) }
    var counter by remember { mutableStateOf(1) }
    val context = LocalContext.current
    val level = 4

    val metinListesi = when (language) {
        "TR" -> listOf(
            "Merhaba! Bugün değişkenleri öğreneceğiz. Hazırsan başlayalım!",
            "Değişkenler, verileri saklamak için kullanılır. Mesela bir ismi, bir sayıyı veya bir sonucu bir değişkende tutabiliriz.",
            "Örneğin: yaş = 10 gibi bir kod, 'yaş' adında bir değişkene 10 değerini verir.",
            "Python'da değişken tanımlarken eşittir işareti (=) kullanılır.",
            "Bir değişkenin sol tarafı ismini, sağ tarafı değerini gösterir.",
            "Kodda şöyle görünür: isim = \"Ali\". Burada isim değişkeni, \"Ali\" bilgisini tutar.",
            "Peki isimleri nasıl belirleyeceğiz? Değişken isimlerinin bazı kuralları vardır.",
            "Bir değişken ismi harf veya alt çizgi (_) ile başlamalıdır, sayı ile başlayamaz.",
            "Boşluk içeremez ve özel karakterler (#, %, !) gibi işaretler kullanılamaz.",
            "Ayrıca Python'un anahtar kelimeleri değişken ismi olarak kullanılamaz. Mesela 'if', 'for' gibi.",
            "Şimdi değişkenlerle ilgili birkaç soru çözelim!"
        )
        "EN" -> listOf(
            "Hello! Today we will learn about variables. Let's begin!",
            "Variables are used to store data. You can store a name, a number, or a result inside a variable.",
            "For example: age = 10 means we are assigning the value 10 to the variable 'age'.",
            "In Python, we define variables using the equal sign (=).",
            "The left side of the equal sign is the name, the right side is the value.",
            "In code, it looks like: name = \"Ali\". Here, the variable 'name' stores the value \"Ali\".",
            "So, how do we name them? Variable names follow some rules.",
            "A variable name must start with a letter or an underscore (_), not a number.",
            "It cannot contain spaces or special characters like #, %, !.",
            "Also, Python's reserved keywords like 'if', 'for' can't be used as variable names.",
            "Now let's solve a few questions about variables!"
        )
        else -> emptyList()
    }

    //-----------------------------------------------------------------------------TiklanabilirAlan

    ClickablePlace(
        counter,
        metinListesi.size + 4,
        metinIndex,
        { counter = it },
        { if (counter <= metinListesi.size) metinIndex = it })

    //-----------------------------------------------------------------------------ArkaPlan

    lvlBackground(backGroundImage, navController)

    //-----------------------------------------------------------------------------KonusmaBalonu

    val balonMetni = if (metinIndex in metinListesi.indices) metinListesi[metinIndex] else ""
    konusmaBalonu(balonMetni,  maskotKobot, counter <= metinListesi.size)

    //-----------------------------------------------------------------------------KodGosterimi

    val codeExample = when (language) {
        "TR" -> """
        yas = 10
        print(yas)
        -> Çıktı: 10 <-
    """.trimIndent()

        "EN" -> """
        age = 10
        print(age)
        -> Output: 10 <-
    """.trimIndent()
        else -> ""
    }
    ShowCode(code = codeExample, isVisible = metinIndex in 2..4)

    val codeExample2 = when (language) {
        "TR" -> """
        isim = "Ali"
        print(isim)
        -> Çıktı: Ali <-
    """.trimIndent()

        "EN" -> """
        name = "Ali"
        print(name)
        -> Output: Ali <-
    """.trimIndent()
        else -> ""
    }
    ShowCode(code = codeExample2, isVisible = metinIndex == 5)

    val codeExample3 = when (language) {
        "TR" -> """
        1yas = 12
        ->HATA<-
    """.trimIndent()

        "EN" -> """
        1age = 12
        ->ERROR<-
    """.trimIndent()
        else -> ""
    }
    ShowCode(code = codeExample3, isVisible = metinIndex == 7)

    val codeExample4 = when (language) {
        "TR" -> """
        %isim = "Batuhan"
        ->HATA<-
    """.trimIndent()

        "EN" -> """
        %age = "Batuhan"
        ->ERROR<-
    """.trimIndent()
        else -> ""
    }
    ShowCode(code = codeExample4, isVisible = metinIndex == 8)

    val codeExample5 = when (language) {
        "TR" -> """
        if = "Gamze"
        ->HATA<-
    """.trimIndent()

        "EN" -> """
        if = "Gamze"
        ->ERROR<-
    """.trimIndent()
        else -> ""
    }
    ShowCode(code = codeExample5, isVisible = metinIndex == 9)

    //-----------------------------------------------------------------------------Sorular

    FillInTheBlankQuestion(
        question = if (language == "TR")
            "Bir değişkene değer atarken kullanılan sembol nedir?"
        else
            "Which symbol is used to assign a value to a variable?",
        codeSnippet = """
        isim (1.―) "Ayşe"
        print(isim)
        -> Ayşe <-
        """.trimIndent(),
        correctAnswers = listOf("="),
        answerCount = 1,
        isVisible = counter == metinListesi.size + 1,
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "Aşağıdaki değişken isimlerinden hangisi geçerli değildir?"
        else
            "Which of the following variable names is invalid?",
        codeSnippet = "",
        options = if (language == "TR")
            listOf("1sayi", "_yas", "isim_soyisim", "yas3")
        else
            listOf("1value", "_age", "name_surname", "age3"),
        correctAnswerIndex = 0,
        solution = if (language == "TR")
            "Değişken ismi sayı ile başlayamaz, bu yüzden '1sayi' geçersizdir."
        else
            "Variable names can't start with a number, so '1value' is invalid.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 2,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "Hangi seçenek Python'da değişken olarak kullanılamaz?"
        else
            "Which one cannot be used as a variable in Python?",
        codeSnippet = "",
        options = if (language == "TR")
            listOf("degisken", "sayi1", "for", "_isim")
        else
            listOf("variable", "num1", "for", "_name"),
        correctAnswerIndex = 2,
        solution = if (language == "TR")
            "'for' Python'da bir anahtar kelimedir, değişken olarak kullanılamaz."
        else
            "'for' is a Python keyword and cannot be used as a variable.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 3,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    //-----------------------------------------------------------------------------Kapanış Balonu

    konusmaBalonu(
        if (language == "TR")
            "Harika iş çıkardın! Artık değişkenleri nasıl tanımlayacağını ve kurallarını biliyorsun. Hadi bir sonraki derse geçelim!"
        else
            "Great job! Now you know how to define variables and their rules. Let's move on to the next lesson!",
        maskotKobot, counter == metinListesi.size + 4
    )

    //-----------------------------------------------------------------------------ProgressBar

    progressBar(
        metinListesi.size + 4,
        counter,
        navController,
        Screen.lvl5.createRoute(language),
        context,
        level,
        language
    )
}

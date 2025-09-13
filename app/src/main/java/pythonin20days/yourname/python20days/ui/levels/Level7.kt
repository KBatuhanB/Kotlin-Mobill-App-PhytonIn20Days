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
import com.example.dersapp.components.MultipleChoiceQuestion
import com.example.dersapp.components.konusmaBalonu
import com.example.dersapp.components.lvlBackground
import com.example.dersapp.components.ShowCode
import com.example.dersapp.components.progressBar

@Composable
fun Level7(navController: NavController, language: String) {

    //----------------------------------------------------------------------------- Varaibles

    val backGroundImage = painterResource(id = R.drawable.bolumarkaplani)
    val maskotKobot = R.drawable.maskotornegin
    var metinIndex by remember { mutableStateOf(0) }
    var counter by remember { mutableStateOf(1) }
    val context = LocalContext.current
    val level = 7

    val metinListesi = when (language) {
        "TR" -> listOf(
            "Merhaba! Bugün tip dönüşümlerini, sayısal işlemler için neden önemli olduğunu öğreneceğiz.",
            "Kullanıcıdan aldığımız veri metindir. Matematiksel işlem için sayıya çevirmeliyiz.",
            "Dönüşüm olmazsa işlemler hata verir veya yanlış sonuç çıkar.",
            "\"5\" + \"3\" ifadesi metin birleşimi yapar ve \"53\" olur.",
            "Sayısal toplama için önce int(\"5\") ve int(\"3\") kullanılır, sonuç 8 olur.",
            "Ondalıklı sayı için float() kullanılır, int() hata verir.",
            "int() ve float() sayıya dönüştürür, böylece matematik yapılabilir.",
            "Sayıyı metinle birleştirmek için str() kullanılır.",
            "Şimdi neler öğrendiğimize bir bakalım!"
        )
        "EN" -> listOf(
            "Hello! Today we'll learn why type conversion is vital for math operations.",
            "Input data is text; we must convert it to numbers for math.",
            "Without conversion, math causes errors or wrong results.",
            "'5' + '3' concatenates strings to '53'.",
            "To add numbers, use int('5') and int('3'), result is 8.",
            "Use float() for decimals; int() causes errors on decimals.",
            "int() and float() convert text to numbers for math.",
            "Use str() to join numbers with text.",
            "Now let's see what we've learned!"
        )
        else -> emptyList()
    }

    //----------------------------------------------------------------------------- ClickablePlace

    ClickablePlace(
        counter,
        metinListesi.size + 6,
        metinIndex,
        { counter = it },
        {if (counter <= metinListesi.size) metinIndex = it}
    )

    //----------------------------------------------------------------------------- ArkaPlan

    lvlBackground(backGroundImage, navController)

    //----------------------------------------------------------------------------- KonusmaBaloinu

    val balonMetni = if (metinIndex in metinListesi.indices) metinListesi[metinIndex] else ""
    konusmaBalonu(balonMetni, maskotKobot, counter <= metinListesi.size)

    //----------------------------------------------------------------------------- Kodlar

    val code = when (language) {
        "TR" -> """
            a = 5
            b = "3"
            print(a + b)
            
            ->HATA<-
        """.trimIndent()
        "EN" -> """
            a = 5
            b = "3"
            print(a + b)
            
            ->ERROR<-
        """.trimIndent()
        else -> ""
    }
    ShowCode(code = code, isVisible = metinIndex in 1..2)

    val code1 = when (language) {
        "TR" -> """
            a = "5"
            b = "3"
            print(a + b)
            
            -> 53 <-
            # Çünkü + operatörü stringleri birleştirir.
        """.trimIndent()
        "EN" -> """
            a = "5"
            b = "3"
            print(a + b)
            
            -> 53 <-
            # Because + joins strings.
        """.trimIndent()
        else -> ""
    }
    ShowCode(code = code1, isVisible = metinIndex == 3)

    val code2 = when (language) {
        "TR" -> """
            a = int("5")
            b = int("3")
            print(a + b)
            
            -> 8 <-
            # int() ile string sayılar tam sayıya çevrilir.
        """.trimIndent()
        "EN" -> """
            a = int("5")
            b = int("3")
            print(a + b)
            
            -> 8 <-
            # int() converts strings to integers.
        """.trimIndent()
        else -> ""
    }
    ShowCode(code = code2, isVisible = metinIndex == 4)

    val code3 = when (language) {
        "TR" -> """
            a = float("5.5")
            b = float("3.2")
            print(a + b)
            
            -> 8.7 <-
            # float() ondalıklı sayıları dönüştürür.
        """.trimIndent()
        "EN" -> """
            a = float("5.5")
            b = float("3.2")
            print(a + b)
            
            -> 8.7 <-
            # float() converts decimals.
        """.trimIndent()
        else -> ""
    }
    ShowCode(code = code3, isVisible = metinIndex in 5..6)

    val code4 = when (language) {
        "TR" -> """
            sonuc = 10
            print("Sonuç: " + str(sonuc))
            
            -> Sonuç: 10 <-
            # str() sayıdan metne çevirir.
        """.trimIndent()
        "EN" -> """
            result = 10
            print("Result: " + str(result))
            
            -> Result: 10 <-
            # str() converts number to string.
        """.trimIndent()
        else -> ""
    }
    ShowCode(code = code4, isVisible = metinIndex == 7)

    //----------------------------------------------------------------------------- Sorular

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "Aşağıdakilerden hangisi '5' ve '3'ü sayısal toplar?"
        else
            "Which adds '5' and '3' numerically?"
        ,
        codeSnippet = """
            a = "5"
            b = "3"
        """.trimIndent(),
        options = if (language == "TR")
            listOf("a + b", "int(a) + int(b)", "str(a) + str(b)", "float(a) + b")
        else
            listOf("a + b", "int(a) + int(b)", "str(a) + str(b)", "float(a) + b"),
        correctAnswerIndex = 1,
        solution = if (language == "TR")
            "'int(a) + int(b)' stringleri tam sayıya çevirir."
        else
            "'int(a) + int(b)' converts strings to integers.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 1,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    FillInTheBlankQuestion(
        question = if (language == "TR")
            "String sayıyı ondalıklı sayıya çeviren fonksiyon için boşlukları doldurun:"
        else
            "Fill in the blanks for the function that converts string to float:",
        codeSnippet = """
        sayi1 = "123"
        sayi2 = (1.____)(sayi1)
        print(type(sayi2))
    """.trimIndent(),
        correctAnswers = listOf("float"),
        answerCount = 1,
        isVisible = counter == metinListesi.size + 2,
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "Ondalıklı sayılar için hangi fonksiyon kullanılır?"
        else
            "Which function is used for decimals?",
        codeSnippet = "",
        options = if (language == "TR")
            listOf("int()", "str()", "float()", "bool()")
        else
            listOf("int()", "str()", "float()", "bool()"),
        correctAnswerIndex = 2,
        solution = if (language == "TR")
            "float() ondalıklı sayılar için kullanılır."
        else
            "float() is used for decimals.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 3,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    CodeOutputQuestion(
        question = if (language == "TR")
            "Aşağıdaki kodun çıktısı nedir?"
        else
            "What is the output of the following code?",
        codeSnippet = """
            x = "4"
            y = "5"
            print(x + y)
    """.trimIndent(),
        solution = "45",
        isVisible = counter == metinListesi.size + 4,
        onAnswerChange = { /* Handle answer change */ },
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    CodeOutputQuestion(
        question = if (language == "TR")
            "Aşağıdaki kodun çıktısı nedir?"
        else
            "What is the output of the following code?",
        codeSnippet = """
            x = "4"
            y = "5"
            print(int(x) + int(y))
    """.trimIndent(),
        solution = "9",
        isVisible = counter == metinListesi.size + 5,
        onAnswerChange = { /* Handle answer change */ },
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    //----------------------------------------------------------------------------- Kapanis Konusmasi

    val closingText = when (language) {
        "TR" -> "Tebrikler! Sayısal işlemler için dönüşümün ne kadar önemli olduğunu da kavradın. İyi çalışmalar!"
        "EN" -> "Congratulations! You also understood why conversions are essential for numerical operations. Good job!"
        else -> ""
    }
    konusmaBalonu(closingText, maskotKobot, counter == metinListesi.size + 6)

    //----------------------------------------------------------------------------- Progress Bar

    progressBar(
        metinListesi.size + 6,
        counter,
        navController,
        Screen.lvl8.createRoute(language),
        context,
        level,
        language
    )
}

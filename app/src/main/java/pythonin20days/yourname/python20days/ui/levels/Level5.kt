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
fun Level5(navController: NavController, language: String) {

    //-----------------------------------------------------------------------------Variables

    val backGroundImage = painterResource(id = R.drawable.bolumarkaplani)
    val maskotKobot = R.drawable.maskotok
    var metinIndex by remember { mutableStateOf(0) }
    var counter by remember { mutableStateOf(1) }
    val context = LocalContext.current
    val level = 5

    val metinListesi = when (language) {
        "TR" -> listOf(
            "Selam! Bugün Python'da veri tiplerini öğreneceğiz. Hazırsan başlayalım!",
            "Python'da farklı veri tipleri vardır. En yaygın olanları: sayılar (int), ondalıklı sayılar (float) ve metinler (str).",
            "Bir tam sayı tanımlamak için int tipini kullanırız. Örneğin, yaş gibi.",
            "Ondalıklı sayılar için float tipi kullanılır. Mesela fiyatlar gibi.",
            "Metinleri ise str (string) tipiyle gösteririz. İsimler, mesajlar buna örnektir.",
            "Bir verinin tipini öğrenmek için type() fonksiyonu kullanılır.",
            "Bazen bir verinin tipini değiştirmek isteyebiliriz. Buna tür dönüştürme (type casting) denir.",
            "Örneğin, bir sayıyı string'e çevirmek için str() fonksiyonu kullanılır.",
            "Benzer şekilde, bir metni tam sayıya çevirmek için int() fonksiyonu kullanılır.",
            "Ancak dikkat! Her metin sayıya dönüştürülemez. Mesela 'Merhaba' ifadesi sayıya çevrilemez ve hata verir.",
            "Şimdi veri tipleriyle ilgili birkaç soru çözelim!"
        )

        "EN" -> listOf(
            "Hi! Today we’ll learn about data types in Python. Let’s begin!",
            "In Python, there are different data types. The most common ones are: numbers (int), decimal numbers (float), and texts (str).",
            "To define an integer, we use the int type. For example, age.",
            "For decimal numbers, we use the float type. Like prices.",
            "Texts are represented with the str (string) type. Names, messages are examples.",
            "To check the type of a value, we use the type() function.",
            "Sometimes, we want to change a value’s type. This is called type casting.",
            "For example, to convert a number to a string, we use the str() function.",
            "Similarly, to convert text to an integer, we use the int() function.",
            "But be careful! Not all texts can be converted to numbers. For example, 'Hello' cannot be converted and will raise an error.",
            "Now let’s solve a few questions about data types!"
        )

        else -> emptyList()
    }

    //-----------------------------------------------------------------------------Tiklanabilir Alan

    ClickablePlace(
        counter,
        metinListesi.size + 6,
        metinIndex,
        { counter = it },
        { if (counter <= metinListesi.size) metinIndex = it })

    //-----------------------------------------------------------------------------Arka Plan

    lvlBackground(backGroundImage, navController)

    //-----------------------------------------------------------------------------Konuşma Balonu

    val balonMetni = if (metinIndex in metinListesi.indices) metinListesi[metinIndex] else ""
    konusmaBalonu(balonMetni, maskotKobot, counter <= metinListesi.size)

    //-----------------------------------------------------------------------------Kod Gösterimleri

    val code1 = when (language) {
        "TR" -> """
            yas = 20
            print(type(yas))
            -> <class 'int'> <-
        """.trimIndent()

        "EN" -> """
            age = 20
            print(type(age))
            -> <class 'int'> <-
        """.trimIndent()

        else -> ""
    }
    ShowCode(code = code1, isVisible = metinIndex == 2)

    val code2 = when (language) {
        "TR" -> """
            oran = 3.14
            print(type(oran))
            -> <class 'float'> <-
        """.trimIndent()

        "EN" -> """
            ratio = 3.14
            print(type(ratio))
            -> <class 'float'> <-
        """.trimIndent()

        else -> ""
    }
    ShowCode(code = code2, isVisible = metinIndex == 3)

    val code3 = when (language) {
        "TR" -> """
            isim = "Ali"
            print(type(isim))
            -> <class 'str'> <-
        """.trimIndent()

        "EN" -> """
            name = "Ali"
            print(type(name))
            -> <class 'str'> <-
        """.trimIndent()

        else -> ""
    }
    ShowCode(code = code3, isVisible = metinIndex == 4)

    val codeTypeFunction = when (language) {
        "TR" -> """
            print(type(5))
            -> <class 'int'> <-
        """.trimIndent()

        "EN" -> """
            print(type(5))
            -> <class 'int'> <-
        """.trimIndent()

        else -> ""
    }
    ShowCode(code = codeTypeFunction, isVisible = metinIndex == 5)

    val code4 = when (language) {
        "TR" -> """
            sayi = 10
            yazi = str(sayi)
            print(yazi)
            -> 10 <-
        """.trimIndent()

        "EN" -> """
            number = 10
            text = str(number)
            print(text)
            -> 10 <-
        """.trimIndent()

        else -> ""
    }
    ShowCode(code = code4, isVisible = metinIndex == 7)

    val code5 = when (language) {
        "TR" -> """
            yazi = "123"
            sayi = int(yazi)
            print(sayi)
            -> 123 <-
        """.trimIndent()

        "EN" -> """
            text = "123"
            number = int(text)
            print(number)
            -> 123 <-
        """.trimIndent()

        else -> ""
    }
    ShowCode(code = code5, isVisible = metinIndex == 8)

    val code6 = when (language) {
        "TR" -> """
            yazi = "Merhaba"
            sayi = int(yazi)
            -> HATA <-
        """.trimIndent()

        "EN" -> """
            text = "Hello"
            number = int(text)
            -> ERROR <-
        """.trimIndent()

        else -> ""
    }
    ShowCode(code = code6, isVisible = metinIndex == 9)

    //-----------------------------------------------------------------------------Sorular

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "Aşağıdakilerden hangisi float türünde bir veridir?"
        else
            "Which of the following is a float value?",
        codeSnippet = "",
        options = if (language == "TR")
            listOf("20", "\"Merhaba\"", "3.14", "True")
        else
            listOf("20", "\"Hello\"", "3.14", "True"),
        correctAnswerIndex = 2,
        solution = if (language == "TR")
            "Float, ondalıklı sayı demektir. 3.14 bir float'tır."
        else
            "Float means decimal number. 3.14 is a float.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 1,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "int(\"abc\") ifadesinin sonucu nedir?"
        else
            "What is the result of int(\"abc\")?",
        codeSnippet = "",
        options = if (language == "TR")
            listOf("abc", "0", "Hata verir", "None")
        else
            listOf("abc", "0", "Error", "None"),
        correctAnswerIndex = 2,
        solution = if (language == "TR")
            "\"abc\" sayıya dönüştürülemez, bu yüzden hata verir."
        else
            "\"abc\" cannot be converted to a number, so it gives an error.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 2,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "str(25) ifadesinin sonucu nedir?"
        else
            "What is the result of str(25)?",
        codeSnippet = "",
        options = if (language == "TR")
            listOf("\"25\"", "25.0", "int", "Hata verir")
        else
            listOf("\"25\"", "25.0", "int", "Error"),
        correctAnswerIndex = 0,
        solution = if (language == "TR")
            "str() fonksiyonu, sayıyı metne çevirir. Sonuç \"25\" olur."
        else
            "The str() function converts a number to text. Result is \"25\".",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 3,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "\"42\" ifadesinin veri tipi nedir?"
        else
            "What is the data type of \"42\"?",
        codeSnippet = "x = \"42\"\nprint(type(x))",
        options = if (language == "TR")
            listOf("<class 'int'>", "<class 'str'>", "<class 'float'>", "Hata verir")
        else
            listOf("<class 'int'>", "<class 'str'>", "<class 'float'>", "Error"),
        correctAnswerIndex = 1,
        solution = if (language == "TR")
            "Çift tırnak içinde yazılan değerler string (yani metin) türündedir. Bu nedenle veri tipi <class 'str'> olacaktır."
        else
            "Values written in double quotes are of type string (text). Therefore, the data type will be <class 'str'>.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 4,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "\"3.14\" string'i int'e çevrilmeye çalışılırsa ne olur?"
        else
            "What happens if you try to convert the string \"3.14\" to an int?",
        codeSnippet = """
            x = "3.14"
            ny = int(x)
            print(y)""".trimIndent(),
        options = if (language == "TR")
            listOf("3", "3.14", "Hata verir", "<class 'int'>")
        else
            listOf("3", "3.14", "Error", "<class 'int'>"),
        correctAnswerIndex = 2,
        solution = if (language == "TR")
            "\"3.14\" ondalıklı bir sayıdır ve doğrudan int'e çevrilemez. Bu nedenle hata verir."
        else
            "\"3.14\" is a decimal number and cannot be directly converted to an int. So it gives an error.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 5,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    FillInTheBlankQuestion(
        question = if (language == "TR")
            "Aşağıdaki kodda \"123\" ifadesini tam sayı yapmak için boşluklara gelecek doğru değerleri girin:"
        else
            "In the following code, enter the correct values in the blanks to make \"123\" an integer:",
        codeSnippet = """
            myStr = "123"
            mtInt = (1.___)(myStr)
            print(type(myInt))
            -> <class '(2.___)'> <-
            """.trimIndent(),
        correctAnswers = listOf("int", "int"),
        answerCount = 2,
        isVisible = counter == metinListesi.size + 6,
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )
    //-----------------------------------------------------------------------------Kapanış Balonu

    konusmaBalonu(
        if (language == "TR")
            "Harika iş çıkardın! Artık veri tiplerini, tür dönüşümünü (typecasting) ve type() fonksiyonunu biliyorsun. Hadi bir sonraki derse geçelim!"
        else
            "Great job! Now you know data types, typecasting, and the type() function. Let's move on to the next lesson!",
        maskotKobot, counter == metinListesi.size + 7
    )

    //-----------------------------------------------------------------------------ProgressBar

    progressBar(
        metinListesi.size + 7,
        counter,
        navController,
        Screen.lvl6.createRoute(language),
        context,
        level,
        language
    )
}

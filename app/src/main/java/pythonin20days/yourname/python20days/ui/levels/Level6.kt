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
import com.example.dersapp.components.FillInTheBlankQuestion
import com.example.dersapp.components.MultipleChoiceQuestion
import com.example.dersapp.components.konusmaBalonu
import com.example.dersapp.components.lvlBackground
import com.example.dersapp.components.ShowCode
import com.example.dersapp.components.progressBar

@Composable
fun Level6(navController: NavController, language: String) {

    //----------------------------------------------------------------------------- Variables

    val backGroundImage = painterResource(id = R.drawable.bolumarkaplani)
    val maskotKobot = R.drawable.maskothi
    var metinIndex by remember { mutableStateOf(0) }
    var counter by remember { mutableStateOf(1) }
    val context = LocalContext.current
    val level = 6

    val metinListesi = when (language) {
        "TR" -> listOf(
            "Merhaba! Bugün Python'da terminalden kullanıcıdan veri alma konusunu işleyeceğiz.",
            "Python'da kullanıcıdan veri almak için input() fonksiyonunu kullanırız.",
            "Kullanıcı, değeri atamak istediği değişkenin ismini yazar ve ardından = işaretinden sonra input() fonksiyonunu kullanır.",
            "input() fonksiyonunun içine yazılan ifade, kullanıcıya ekranda gösterilir; böylece kullanıcı ne girmesi gerektiğini anlar.",
            "input() fonksiyonu kullanıcıdan aldığı veriyi her zaman metin (string) olarak döndürür.",
            "Kullanıcıdan alınan veri string olduğu için, sayısal işlemler yapmak istiyorsak veri tipini dönüştürmemiz gerekir.",
            "Sayısal dönüştürme için int() veya float() fonksiyonlarını kullanırız.",
            "Örneğin, yaş bilgisini int() ile dönüştürerek sayısal işlemler yapabiliriz.",
            "Şimdi terminalden kullanıcıdan veri alma ile ilgili kod örneklerini inceleyelim."
        )
        "EN" -> listOf(
            "Hello! Today we will learn how to get input from the user in the Python terminal.",
            "We use the input() function to get user input in Python.",
            "The user writes the name of the variable to assign the value to, then uses the input() function after the = sign.",
            "The string inside the input() function is displayed as a prompt to the user, so they know what to enter.",
            "The input() function always returns the data entered by the user as a string.",
            "Because the input data is a string, if we want to perform numerical operations, we need to convert its type.",
            "We use int() or float() functions to convert the input to numeric types.",
            "For example, we can convert the age input to an integer using int() to perform calculations.",
            "Now let's look at some code examples of getting user input from the terminal."
            )
        else -> emptyList()
    }

    //----------------------------------------------------------------------------- Clickable Place (for navigation dots)

    ClickablePlace(
        counter,
        metinListesi.size + 5,
        metinIndex,
        { counter = it },
        { if (counter <= metinListesi.size) metinIndex = it })

    //----------------------------------------------------------------------------- Background

    lvlBackground(backGroundImage, navController)

    //----------------------------------------------------------------------------- Speech Balloon

    val balonMetni = if (metinIndex in metinListesi.indices) metinListesi[metinIndex] else ""
    konusmaBalonu(balonMetni, maskotKobot, counter <= metinListesi.size)

    //----------------------------------------------------------------------------- Code Displays

    val code1 = when (language) {
        "TR" -> """
        isim = input("Adınızı girin: ")
        print("Merhaba", isim)
        
        -> Adınızı girin: Ahmet <-
        # Kullanıcı Ahmet yazıyor. 
        # Kullanıcı yazana kadar bekler. 
        
        -> Merhaba Ahmet <-
        # Ekrana "Merhaba Ahmet" yazıyor.
    """.trimIndent()
        "EN" -> """
        name = input("Enter your name: ")
        print("Hello", name)
        
        -> Enter your name: Ahmet <-
        # User types Ahmet. 
        # Waits until user types. 
        
        -> Hello Ahmet <-
        # "Hello Ahmet" is written on the screen.
    """.trimIndent()
        else -> ""
    }
    ShowCode(code = code1, isVisible = metinIndex in 2..3)

    val code2 = when (language) {
        "TR" -> """
        yas = input("Yaşınızı girin: ")
        print("Girdiğiniz veri:", yas)
        print("Verinin tipi:", type(yas))
        
        -> Yaşınızı girin: 25 <-
        # Kullanıcı 25 yazıyor.
        # input() fonksiyonu veriyi string olarak alır.
        
        -> Girdiğiniz veri: 25 <-
        -> Verinin tipi: <class 'str'> <-
        # Tip olarak string (str) döner.
    """.trimIndent()
        "EN" -> """
        age = input("Enter your age: ")
        print("You entered:", age)
        print("Type of the input:", type(age))
        
        -> Enter your age: 25 <-
        # User types 25.
        # input() returns the data as a string.
        
        -> You entered: 25 <-
        -> Type of the input: <class 'str'> <-
        # The type is string (str).
    """.trimIndent()
        else -> ""
    }
    ShowCode(code = code2, isVisible = metinIndex in 4..5)

    val code3 = when (language) {
        "TR" -> """
        yas = int(input("Yaşınızı girin: "))
        sonraki_yil = yas + 1
        print("Bir sonraki yıl yaşınız:", sonraki_yil)
        
        -> Yaşınızı girin: 25 <-
        # Kullanıcı 25 yazıyor. 
        # Kullanıcı yazana kadar bekler.
        
        -> Bir sonraki yıl yaşınız: 26 <-
        # Ekrana "Bir sonraki yıl yaşınız: 26" yazıyor.
    """.trimIndent()
        "EN" -> """
        age = int(input("Enter your age: "))
        next_year = age + 1
        print("Your age next year will be:", next_year)
        
        -> Enter your age: 25 <-
        # User types 25. 
        # Waits until user types.
        
        -> Your age next year will be: 26 <-
        # "Your age next year will be: 26" is written on the screen.
    """.trimIndent()
        else -> ""
    }
    ShowCode(code = code3, isVisible = metinIndex in 6..7)

    //----------------------------------------------------------------------------- Questions

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "input() fonksiyonunun döndürdüğü veri tipi nedir?"
        else
            "What is the data type returned by the input() function?",
        codeSnippet = "",
        options = if (language == "TR")
            listOf("int", "str", "float", "bool")
        else
            listOf("int", "str", "float", "bool"),
        correctAnswerIndex = 1,
        solution = if (language == "TR")
            "input() fonksiyonu her zaman string (str) türünde veri döndürür."
        else
            "The input() function always returns data as a string (str).",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 1,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "Aşağıdakilerden hangisi kullanıcıdan alınan yaş verisini doğru şekilde tam sayıya çevirir?"
        else
            "Which of the following correctly converts the user's age input to an integer?",
        codeSnippet = "",
        options = if (language == "TR")
            listOf(
                "yas = input('Yaşınızı girin: ')\nyas = int(yas)",
                "yas = int(input('Yaşınızı girin: '))",
                "yas = float(input('Yaşınızı girin: '))",
                "yas = str(input('Yaşınızı girin: '))"
            )
        else
            listOf(
                "age = input('Enter your age: ')\nage = int(age)",
                "age = int(input('Enter your age: '))",
                "age = float(input('Enter your age: '))",
                "age = str(input('Enter your age: '))"
            ),
        correctAnswerIndex = 1,
        solution = if (language == "TR")
            "int() fonksiyonunu doğrudan input() fonksiyonunun üzerine yazmak, doğru ve kısa yoldur."
        else
            "Using int() directly around input() is the correct and concise way.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 2,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "Aşağıdaki kodda kullanıcıdan alınan veri kaçıncı satırda string'den tam sayıya dönüştürülüyor?"
        else
            "In the code below, at which line is the user input converted from string to integer?",
        codeSnippet = """
            1: yas = input("Yaşınızı girin: ")
            2: print(type(yas))
            3: yas = int(yas)
            4: print(type(yas))
        """.trimIndent(),
        options = if (language == "TR")
            listOf("1", "2", "3", "4")
        else
            listOf("1", "2", "3", "4"),
        correctAnswerIndex = 2,
        solution = if (language == "TR")
            "3. satırda input'tan gelen string int() ile tam sayıya dönüştürülüyor."
        else
            "At line 3, the string input is converted to an integer using int().",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 3,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    FillInTheBlankQuestion(
        question = if (language == "TR")
            "Aşağıdaki kodda kullanıcıdan alınan değeri tam sayıya çevirmek için boşlukları doldurun:"
        else
            "Fill in the blanks to convert the user input to an integer:",
        codeSnippet = """
            x = (1.____)( "Bir sayı girin: " )
            y = (2.____)(x)
            print(type(y))
            -> <class 'int'> <-
        """.trimIndent(),
        correctAnswers = listOf("input", "int"),
        answerCount = 2,
        isVisible = counter == metinListesi.size + 4,
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    //----------------------------------------------------------------------------- Closing speech balloon after questions

    val closingText = when (language) {
        "TR" -> "Tebrikler! Artık kullanıcıdan veri almayı öğrendin. İyi çalışmalar!"
        "EN" -> "Congratulations! You have learned how to get user input. Good job!"
        else -> ""
    }
    konusmaBalonu(closingText, maskotKobot, counter == metinListesi.size + 5)

    //----------------------------------------------------------------------------- Progress Bar

    progressBar(
        metinListesi.size + 5,
        counter,
        navController,
        Screen.lvl7.createRoute(language),
        context,
        level,
        language
    )

}
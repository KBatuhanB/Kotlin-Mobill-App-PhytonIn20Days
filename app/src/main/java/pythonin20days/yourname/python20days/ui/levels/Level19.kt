package com.example.dersapp.levels

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.yourname.python20days.R
import com.example.dersapp.components.*
import com.example.dersapp.data.Screen

@Composable
fun Level19(navController: NavController, language: String) {

    //---------------------------------------------------------------------------- Variables

    val backGroundImage = painterResource(id = R.drawable.bolumarkaplani)
    val maskotKobot = R.drawable.maskotpc
    var metinIndex by remember { mutableStateOf(0) }
    var counter by remember { mutableStateOf(1) }
    val context = LocalContext.current
    val level = 19

    //---------------------------------------------------------------------------- Texts

    val metinListesi = when (language) {
        "TR" -> listOf(
            "Bugünkü konumuz: Python modülleri ve `import` kullanımı.",
            "Modüller, Python dosyalarıdır ve içinde fonksiyonlar, değişkenler veya sınıflar bulunabilir.",
            "Kodlarımızı daha düzenli hale getirmek ve tekrar kullanmak için modüller çok faydalıdır.",
            "`import` anahtar kelimesi ile başka bir dosyadaki kodları programımıza dahil edebiliriz.",
            "Python, bize birçok hazır modül sunar. Bunlara 'standart modüller' denir.",
            "`math` modülü matematiksel işlemler için kullanılır. Örneğin karekök alma, pi sayısı vb.",
            "`random` modülü rastgele sayılar üretmek için kullanılır.",
            "Bu modülleri kullanmadan önce `import` etmeliyiz. Örneğin:\n`import math` veya `import random`",
            "Modül içindeki bir özelliğe ulaşmak için `modül_adı.özellik` şeklinde yazarız.",
            "Örneğin: `math.sqrt(16)` → 16'nın karekökünü alır ve `4.0` sonucunu verir.",
            "Bir başka örnek: `random.randint(1, 10)` → 1 ile 10 arasında rastgele bir sayı üretir.",
            "Kodlarımızı daha güçlü ve esnek hale getirmek için modülleri kullanmak çok önemlidir.",
            "Artık birlikte bazı örnekleri inceleyelim."
        )
        "EN" -> listOf(
            "Today's topic: Python modules and using `import`.",
            "Modules are Python files that can contain functions, variables, or classes.",
            "They help us organize code better and reuse functionality easily.",
            "We use the `import` keyword to bring in code from another module.",
            "Python provides many built-in modules. These are called standard modules.",
            "`math` module is for mathematical operations like square root, pi, etc.",
            "`random` module is used to generate random numbers.",
            "Before using them, we must import them. For example: `import math` or `import random`",
            "To access a feature in a module, we use `module_name.feature` format.",
            "Example: `math.sqrt(16)` → returns `4.0` (square root of 16)",
            "Another example: `random.randint(1, 10)` → gives a random number between 1 and 10.",
            "Using modules makes our programs more powerful and flexible.",
            "Now let’s check some examples together."
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
    konusmaBalonu(balonMetni,  maskotKobot, counter <= metinListesi.size)

    //---------------------------------------------------------------------------- Code Blocks

    val codeExample1 = if (language == "EN") {
        """
        import math
        import random
        """.trimIndent()
    } else {
        """
        import math
        import random
        """.trimIndent()
    }
    ShowCode(codeExample1, isVisible = counter == 8)

    val codeExample3 = if (language == "EN") {
        """
        import math
        
        print(math.sqrt(16))
        -> 4.0 <-
        """.trimIndent()
    } else {
        """
        import math
        
        print(math.sqrt(16))
        -> 4.0 <-
        """.trimIndent()
    }
    ShowCode(codeExample3, isVisible = counter == 10)

    val codeExample2 = if (language == "TR") {
        """
        import random
        
        print(random.randint(1, 10))
        #rastgele sayı üretir ve yazdırır.
        """.trimIndent()
    } else {
        """
        import random
        
        print(random.randint(1, 10))
        #It generates a random number and prints it.
        """.trimIndent()
    }
    ShowCode(codeExample2, isVisible = counter == 11)

    //---------------------------------------------------------------------------- Code Output Questions

    CodeOutputQuestion(
        question = if (language == "TR") "Bu kod ne yazdırır?" else "What does this code print?",
        codeSnippet = """
            import math
            print(math.sqrt(16))
        """.trimIndent(),
        solution = "4.0",
        isVisible = counter == metinListesi.size + 1,
        onAnswerChange = {},
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    CodeOutputQuestion(
        question = if (language == "TR") "Bu kod ne yazdırır?" else "What does this code print?",
        codeSnippet = """
            import random
            print(random.randint(1, 1))
        """.trimIndent(),
        solution = "1",
        isVisible = counter == metinListesi.size + 2,
        onAnswerChange = {},
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    //---------------------------------------------------------------------------- Fill in the Blank

    FillInTheBlankQuestion(
        question = if (language == "TR") "Eksik olan modül adını yazın:" else "Write the missing module name:",
        codeSnippet = """
            import (1.____)
            print(math.pi)
        """.trimIndent(),
        correctAnswers = listOf("math"),
        answerCount = 1,
        isVisible = counter == metinListesi.size + 3,
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    FillInTheBlankQuestion(
        question = if (language == "TR") "Eksik olan fonksiyon adını yazın:" else "Write the missing function name:",
        codeSnippet = """
            import random
            print(random.(1.____)(1, 5))
        """.trimIndent(),
        correctAnswers = listOf("randint"),
        answerCount = 1,
        isVisible = counter == metinListesi.size + 4,
        onDismissLeft = { counter-- },
        onDismissRight = { counter++ }
    )

    //---------------------------------------------------------------------------- Multiple Choice

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "`math` modülünü kullanmadan önce ne yapmalıyız?"
        else
            "What should we do before using `math` module?",
        codeSnippet = "",
        options = if (language == "TR")
            listOf("print(math.pi)", "math.print(pi)", "import math", "math = import")
        else
            listOf("print(math.pi)", "math.print(pi)", "import math", "math = import"),
        correctAnswerIndex = 2,
        solution = if (language == "TR")
            "`math` modülünü kullanmadan önce `import math` yazmalıyız."
        else
            "We must write `import math` before using it.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 5,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "`random.randint(1, 5)` ne yapar?"
        else
            "What does `random.randint(1, 5)` do?",
        codeSnippet = "",
        options = if (language == "TR")
            listOf(
                "1 ile 5 arasında sabit bir sayı verir",
                "1 ile 5 arasında rastgele bir sayı verir",
                "5'in karekökünü alır",
                "Bir hata verir"
            )
        else
            listOf(
                "Gives a fixed number between 1 and 5",
                "Gives a random number between 1 and 5",
                "Calculates square root of 5",
                "Raises an error"
            ),
        correctAnswerIndex = 1,
        solution = if (language == "TR")
            "`random.randint(1, 5)` fonksiyonu 1 ile 5 arasında rastgele bir tamsayı döndürür."
        else
            "`random.randint(1, 5)` returns a random integer between 1 and 5.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 6,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    //---------------------------------------------------------------------------- End Message

    val closingText = when (language) {
        "TR" -> "Harika! Artık modülleri nasıl kullanacağını ve `import` ile başka dosyalardaki özellikleri nasıl çağıracağını biliyorsun!"
        "EN" -> "Awesome! Now you know how to use modules and call external features using `import`!"
        else -> ""
    }

    konusmaBalonu(closingText, maskotKobot, counter == metinListesi.size + 7)

    //---------------------------------------------------------------------------- Progress Bar

    progressBar(
        metinListesi.size + 7,
        counter,
        navController,
        Screen.lvl20.createRoute(language),
        context,
        level,
        language
    )
}

package com.example.dersapp.levels

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.yourname.python20days.R
import com.example.dersapp.components.*
import com.example.dersapp.data.Screen

@Composable
fun Level15(navController: NavController, language: String) {

    //---------------------------------------------------------------------------- Variables

    val backGroundImage = painterResource(id = R.drawable.bolumarkaplani)
    val maskotKobot = R.drawable.maskotok
    var metinIndex by remember { mutableStateOf(0) }
    var counter by remember { mutableStateOf(1) }
    val context = LocalContext.current
    val level = 15

    //---------------------------------------------------------------------------- Texts

    val metinListesi = when (language) {
        "TR" -> listOf(
            "Bugün Python'da listeleri öğreneceğiz!",
            "Liste, birden fazla veriyi bir arada tutmamıza yarayan bir yapıdır.",
            "Örnek bir liste: `meyveler = [\"elma\", \"muz\", \"çilek\"]`",
            "Listelerdeki elemanlara sırasıyla 0'dan başlayarak ulaşabiliriz.",
            "Örneğin: `print(meyveler[0])` ekrana `elma` yazdırır.",
            "Listelere yeni bir eleman eklemek için `append()` metodunu kullanırız.",
            "Örneğin: \n`meyveler.append(\"portakal\")`",
            "Bir elemanı listeden silmek için `remove()` metodunu veya `del` komutunu kullanabiliriz.",
            "Örnek: \n`meyveler.remove(\"muz\")` \n→ \"muz\" listeden silinir.",
            "Ya da: \n`del meyveler[0]`\n→ 0. indeksteki eleman silinir.",
            "Python'da `len()` fonksiyonu, bir listenin, stringin veya diğer koleksiyonların uzunluğunu verir.",
            "Örneğin: `len(\"merhaba\")` sonucu `7` olur çünkü 'merhaba' 7 harften oluşur.",
            "Bir liste için kullanırsak: `len([1, 2, 3])` sonucu `3` olur.",
            "`len()` fonksiyonu, kaç eleman olduğunu saymak için çok faydalıdır.",
            "Genellikle döngülerde veya veri kontrolü yaparken kullanılır.",
            "Listeler kodda çok sık kullanılır çünkü birden fazla veriyi kolayca yönetmemizi sağlar.",
            "Şimdi birkaç örnekle bilgimizi test edelim!"
        )
        "EN" -> listOf(
            "Today we'll learn about lists in Python!",
            "A list is a structure that allows us to store multiple values together.",
            "An example list: `fruits = [\"apple\", \"banana\", \"strawberry\"]`",
            "We can access items in a list by their index, starting from 0.",
            "Example: `print(fruits[0])` prints `apple` on the screen.",
            "To add a new item to a list, we use the `append()` method.",
            "Example: \n`fruits.append(\"orange\")`",
            "To remove an item from a list, we can use the `remove()` method or the `del` command.",
            "Example: \n`fruits.remove(\"banana\")` \n→ removes \"banana\" from the list.",
            "Or: \n`del fruits[0]` \n→ deletes the item at index 0.",
            "In Python, the `len()` function returns the length of a list, string, or other collections.",
            "For example: `len(\"hello\")` returns `5` because 'hello' has 5 characters.",
            "If we use it on a list: `len([1, 2, 3])` returns `3`.",
            "The `len()` function is very useful for counting how many items there are.",
            "It’s often used in loops or when checking data.",
            "Lists are used very often in code because they help us manage multiple values easily.",
            "Now let’s test your understanding with a few examples!"
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

    val listCode0 = """
        meyveler = ["elma", "muz", "kiraz"]
    """.trimIndent()

    ShowCode(listCode0, isVisible = metinIndex == 2)

    val listCode = """
        meyveler = ["elma", "muz", "kiraz"]
        print(meyveler[1])
        
        -> muz <-
    """.trimIndent()

    ShowCode(listCode, isVisible = metinIndex in 3..4)

    val appendCode = """
        sayilar = [1, 2, 3]
        sayilar.append(4)
        print(sayilar)
        
        -> [1, 2, 3, 4] <-
    """.trimIndent()

    ShowCode(appendCode, isVisible = metinIndex in 5..6)

    val removeCode = """
        sayilar = [1, 2, 3]
        sayilar.remove(0)
        print(sayilar)
        
        -> [2, 3] <-
    """.trimIndent()

    ShowCode(removeCode, isVisible = metinIndex in 7..9)

    val codeExample4 = when (language) {
        "TR" -> """
        isim = "Ahmet"
        sayi_listesi = [10, 20, 30, 40]
        print(len(isim))          
        -> 5 <-
        print(len(sayi_listesi))
        -> 4 <-
    """.trimIndent()

        "EN" -> """
        name = "Ahmet"
        number_list = [10, 20, 30, 40]
        print(len(name))           
        -> 5 <-
        print(len(number_list))
        -> 4 <-
    """.trimIndent()

        else -> ""
    }
    ShowCode(codeExample4, isVisible = metinIndex in 10..14)
    //---------------------------------------------------------------------------- Questions

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "Aşağıdaki kodun çıktısı nedir?"
        else
            "What is the output of the following code?",
        codeSnippet = if (language == "TR")
            """
            meyveler = ["elma", "muz", "kiraz"]
            print(meyveler[0])
            """.trimIndent()
        else
            """
            fruits = ["apple", "banana", "cherry"]
            print(fruits[0])
            """.trimIndent(),
        options = if (language == "TR")
            listOf("elma", "muz", "kiraz", "0")
        else
            listOf("apple", "banana", "cherry", "0"),
        correctAnswerIndex = 0,
        solution = if (language == "TR")
            "İndeksler 0'dan başlar. İlk eleman 'elma'dır."
        else
            "Indexes start from 0. The first element is 'apple'.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 1,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "Aşağıdaki koddan sonra 'sayilar' listesi ne olur?"
        else
            "What will the list 'numbers' be after this code?",
        codeSnippet = """
            sayilar = [1, 2, 3]
            sayilar.append(5)
        """.trimIndent(),
        options = if (language == "TR")
            listOf("[1, 2, 3]", "[5, 1, 2, 3]", "[1, 2, 3, 5]", "[1, 2, 5]")
        else
            listOf("[1, 2, 3]", "[5, 1, 2, 3]", "[1, 2, 3, 5]", "[1, 2, 5]"),
        correctAnswerIndex = 2,
        solution = if (language == "TR")
            "append() metodu listenin sonuna eleman ekler."
        else
            "The append() method adds the item to the end of the list.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 2,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "Hangi ifade listedeki eleman sayısını verir?"
        else
            "Which expression gives the number of elements in a list?",
        codeSnippet = """
            sayilar = [4, 5, 6, 7]
        """.trimIndent(),
        options = if (language == "TR")
            listOf("sayilar.size()", "count(sayilar)", "len(sayilar)", "sayilar.length")
        else
            listOf("numbers.size()", "count(numbers)", "len(numbers)", "numbers.length"),
        correctAnswerIndex = 2,
        solution = if (language == "TR")
            "len() fonksiyonu liste uzunluğunu verir."
        else
            "len() function returns the length of the list.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 3,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    MultipleChoiceQuestion(
        question = if (language == "TR")
            "Listelerde ilk elemanın indeksi kaçtır?"
        else
            "What is the index of the first element in a list?",
        codeSnippet = """
            renkler = ["kırmızı", "yeşil", "mavi"]
        """.trimIndent(),
        options = if (language == "TR")
            listOf("1", "0", "-1", "ilk")
        else
            listOf("1", "0", "-1", "first"),
        correctAnswerIndex = 1,
        solution = if (language == "TR")
            "Listelerde indeksleme 0'dan başlar."
        else
            "List indexes start from 0.",
        onAnswerSelected = {},
        isVisible = counter == metinListesi.size + 4,
        onDismissRight = { counter++ },
        onDismissLeft = { counter-- }
    )

    //---------------------------------------------------------------------------- End Message

    val closingText = when (language) {
        "TR" -> "Harika! Artık listelerin nasıl tanımlandığını, indeksleme ve eleman ekleme işlemlerini biliyorsun."
        "EN" -> "Awesome! Now you know how to define lists, index them, and add new elements."
        else -> ""
    }

    konusmaBalonu(closingText, maskotKobot, counter == metinListesi.size + 5)

    //---------------------------------------------------------------------------- Progress Bar

    progressBar(
        metinListesi.size + 5,
        counter,
        navController,
        Screen.lvl16.createRoute(language),
        context,
        level,
        language
    )
}

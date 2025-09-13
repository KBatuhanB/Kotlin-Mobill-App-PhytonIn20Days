package com.example.dersapp.levels

import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.yourname.python20days.R
import com.example.dersapp.components.ClickablePlace
import com.example.dersapp.components.konusmaBalonu
import com.example.dersapp.components.lvlBackground
import com.example.dersapp.components.progressBar
import com.example.dersapp.data.Screen

@Composable
fun Level20(navController: NavController, language: String) {

    //----------------------------------------------------------------------------- Variables

    val backGroundImage = painterResource(id = R.drawable.bolumarkaplani)
    val maskotKobot = R.drawable.maskotok
    var metinIndex by remember { mutableStateOf(0) }
    var counter by remember { mutableStateOf(1) }
    val context = LocalContext.current
    val level = 20

    //----------------------------------------------------------------------------- Texts

    val metinListesi = when (language) {
        "TR" -> listOf(
            "Tebrikler! Python serüveninde 20 günü tamamladın ve temel programlama bilgilerini başarıyla öğrendin.",
            "Bu derslerde değişkenleri, döngüleri, koşulları, fonksiyonları ve daha fazlasını tanıdın.",
            "Kodların satır satır nasıl çalıştığını, bilgisayarın mantığını ve hata ayıklamayı öğrendin.",
            "Artık programlamaya giriş yaptın ve temel araçlara sahipsin.",
            "Bu noktadan sonra ilerlemek için daha derin kaynaklara göz atabilir ve projeler yapmaya başlayabilirsin.",
            "En önemlisi, kodları gerçek bir bilgisayarda yazarak bolca pratik yapmalısın.",
            "Kod yazmak, öğrenmenin en güçlü yoludur!",
            "Unutma: Her hata, seni daha iyi bir programcı yapar.",
            "Bu yolculukta sana eşlik etmek benim için büyük bir keyifti!",
            "Ben KOBOT, her zaman senin yanındayım. Kodlamaya devam et!"
        )
        "EN" -> listOf(
            "Congratulations! You've completed 20 days of Python and successfully learned the basics of programming.",
            "In these lessons, you explored variables, loops, conditions, functions, and more.",
            "You discovered how code runs step by step and how a computer 'thinks'.",
            "You now have the fundamental tools to build programs.",
            "To progress further, explore more advanced resources and start building projects.",
            "Most importantly, practice by writing code on a real computer.",
            "Writing code is the most powerful way to learn!",
            "Remember: Every error is a chance to grow as a programmer.",
            "It was a pleasure accompanying you on this learning adventure!",
            "I'm KOBOT, always by your side. Keep coding!"
        )
        else -> emptyList()
    }

    //----------------------------------------------------------------------------- Clickable Area

    ClickablePlace(counter, metinListesi.size, metinIndex, { counter = it }, {if (counter <= metinListesi.size) metinIndex = it})

    //----------------------------------------------------------------------------- Background

    lvlBackground(backGroundImage, navController)

    //----------------------------------------------------------------------------- Speech Bubble

    val balonMetni = if (metinIndex in metinListesi.indices) metinListesi[metinIndex] else ""
    konusmaBalonu(balonMetni, maskotKobot, counter <= metinListesi.size)

    //----------------------------------------------------------------------------- Progress Bar

    progressBar(
        metinListesi.size,
        counter,
        navController,
        Screen.Home.route, // veya başka bir ana ekrana dönüş olabilir
        context,
        level,
        language
    )
}

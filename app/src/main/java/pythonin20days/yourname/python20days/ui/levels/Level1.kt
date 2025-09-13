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
import com.example.dersapp.components.konusmaBalonu
import com.example.dersapp.components.lvlBackground
import com.example.dersapp.components.progressBar
import com.example.dersapp.data.Screen

@Composable
fun Level1(navController: NavController, language: String) {
    val backGroundImage = painterResource(id = R.drawable.bolumarkaplani)
    val maskotKobot = R.drawable.maskothi
    var metinIndex by remember { mutableStateOf(0) }
    var counter by remember { mutableStateOf(1) }
    val context = LocalContext.current
    val level = 1

    val metinListesi = when (language) {
        "TR" -> listOf(
            "Merhaba! Ben KOBOT. Bu uygulamada seninle Python programlamayı öğreneceğiz!",
            "Programlama, bilgisayara ne yapacağını adım adım anlatmaktır.",
            "Bir program yazmak, tarif yazmaya benzer. Bilgisayar bu tarifi takip eder.",
            "Kod dediğimiz şey, bilgisayara verdiğimiz bu adım adım talimatlardır.",
            "Python, yazması kolay ve eğlenceli bir programlama dilidir.",
            "İlk dersimizde kodun ne olduğunu ve nasıl çalıştığını keşfedeceğiz.",
            "Hazırsan başlayalım ve Python dünyasına birlikte adım atalım!"
        )
        "EN" -> listOf(
            "Hello! I'm KOBOT. In this app, we will learn Python programming together!",
            "Programming means telling the computer what to do step by step.",
            "Writing a program is like writing a recipe. The computer follows the steps.",
            "The instructions we give to the computer are called code.",
            "Python is a fun and easy-to-write programming language.",
            "In our first lesson, we'll explore what code is and how it works.",
            "If you're ready, let's begin and step into the world of Python together!"
        )
        else -> emptyList()
    }

    ClickablePlace(
        counter,
        metinListesi.size,
        metinIndex,
        { counter = it },
        { if (counter <= metinListesi.size) metinIndex = it }
    )

    lvlBackground(backGroundImage, navController)

    val balonMetni = if (metinIndex in metinListesi.indices) metinListesi[metinIndex] else ""
    konusmaBalonu(balonMetni,  maskotKobot, counter <= metinListesi.size)

    progressBar(
        metinListesi.size,
        counter,
        navController,
        Screen.lvl2.createRoute(language),
        context,
        level,
        language
    )
}
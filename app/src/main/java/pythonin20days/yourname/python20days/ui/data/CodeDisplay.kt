package com.example.dersapp.data

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CodeDisplay(
    code: String,
    modifier: Modifier = Modifier
) {
    // Regex desenleri
    val arrowPattern = Regex("""->.*?<\-""", RegexOption.DOT_MATCHES_ALL)  // Yeni regex
    val commentPattern = Regex("#.*")
    val stringPattern = Regex("\"\"\".*?\"\"\"|'''.*?'''|\".*?\"|'.*?'", RegexOption.DOT_MATCHES_ALL)
    val keywordPattern = Regex("""\b(False|class|finally|is|return|None|continue|for|lambda|try|True|def|from|nonlocal|while|and|del|global|not|with|as|elif|if|or|yield|assert|else|import|pass|break|except|in|raise)\b""")
    val numberPattern = Regex("""\b\d+(\.\d+)?\b""")
    val decoratorPattern = Regex("""@\w+""")
    val builtinFuncPattern = Regex("""\b(print|len|range|int|float|str|list|dict|set|tuple|type|isinstance|super|object)\b""")

    val annotatedString = buildAnnotatedString {
        var index = 0
        while (index < code.length) {
            // Yeni: -> ... <- arası kontrolü (öncelikli)
            val arrowMatch = arrowPattern.find(code, index)
            if (arrowMatch != null && arrowMatch.range.first == index) {
                withStyle(
                    SpanStyle(
                        color = Color(0xFAB013CE),  // Koyu kırmızı
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                    )
                ) {
                    append(arrowMatch.value)
                }
                index = arrowMatch.range.last + 1
                continue
            }

            // Öncelikle yorum araması
            val commentMatch = commentPattern.find(code, index)
            if (commentMatch != null && commentMatch.range.first == index) {
                withStyle(SpanStyle(color = Color(0xFF008000))) { // yeşil
                    append(commentMatch.value)
                }
                index = commentMatch.range.last + 1
                continue
            }

            // Decorator (@ ile başlayan)
            val decoratorMatch = decoratorPattern.find(code, index)
            if (decoratorMatch != null && decoratorMatch.range.first == index) {
                withStyle(SpanStyle(color = Color(0xFF8F6B0B))) { // mor
                    append(decoratorMatch.value)
                }
                index = decoratorMatch.range.last + 1
                continue
            }

            // String araması
            val stringMatch = stringPattern.find(code, index)
            if (stringMatch != null && stringMatch.range.first == index) {
                withStyle(SpanStyle(color = Color(0xFFA31515))) { // koyu kırmızı
                    append(stringMatch.value)
                }
                index = stringMatch.range.last + 1
                continue
            }

            // Keyword araması
            val keywordMatch = keywordPattern.find(code, index)
            if (keywordMatch != null && keywordMatch.range.first == index) {
                withStyle(SpanStyle(color = Color(0xFF0000FF))) { // mavi
                    append(keywordMatch.value)
                }
                index = keywordMatch.range.last + 1
                continue
            }

            // Built-in fonksiyonlar
            val builtinMatch = builtinFuncPattern.find(code, index)
            if (builtinMatch != null && builtinMatch.range.first == index) {
                withStyle(SpanStyle(color = Color(0xFF267f99))) { // turkuaz
                    append(builtinMatch.value)
                }
                index = builtinMatch.range.last + 1
                continue
            }

            // Sayılar
            val numberMatch = numberPattern.find(code, index)
            if (numberMatch != null && numberMatch.range.first == index) {
                withStyle(SpanStyle(color = Color(0xFF098658))) { // yeşil tonları
                    append(numberMatch.value)
                }
                index = numberMatch.range.last + 1
                continue
            }

            // Fonksiyon isimleri (basit varsayım: def sonrası gelen isim)
            val defIndex = code.indexOf("def ", index)
            if (defIndex == index) {
                append("def ")
                val startName = index + 4
                val endName = code.indexOf('(', startName).takeIf { it != -1 } ?: code.length
                withStyle(SpanStyle(color = Color(0xFFFF9800))) { // kahverengi
                    append(code.substring(startName, endName))
                }
                index = endName
                continue
            }

            // Operatör veya normal karakter
            append(code[index])
            index++
        }
    }

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    val fontSizeSp = (screenWidthDp * 0.038f).sp      // Ör: 360dp ekranda ~13.7sp, 400dp'de ~15.2sp
    val lineHeightSp = (screenWidthDp * 0.058f).sp    // Oranını dilediğine göre ayarla
    val letterSpacingSp = (screenWidthDp * 0.0013f).sp // Öneri: 0.2sp için

    Text(
        text = annotatedString,
        style = MaterialTheme.typography.bodyMedium.copy(
            fontFamily = FontFamily.Monospace,
            fontSize = fontSizeSp,
            lineHeight = lineHeightSp,
            letterSpacing = letterSpacingSp
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CodeDisplayPreview() {
    val sampleCode = """
        
        
        
        # Bu bir yorum satırı
        def merhaba():
            print("Merhaba Dünya!")  # Bu bir yorum
            
        # Koşul örneği
        if 5 > 2:
            print("Beş, ikiden büyüktür!")
        else:
            print("Bu yazı görünmeyecek")
            
        # Sayılar ve stringler
        sayi = 42
        metin = "Python harika!"
    """.trimIndent()
    
    CodeDisplay(
        code = sampleCode,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

package com.negolatina.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.negolatina.app.R

val Popins = FontFamily(Font(R.font.popins))

val Black= FontFamily(Font(R.font.black))
val Breesefir = FontFamily(Font(R.font.breeesefir))

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = Breesefir,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Popins,
        fontWeight = FontWeight.Black,
        fontSize = 27.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Black,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    )

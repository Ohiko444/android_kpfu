package com.example.navigation_3

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Screen1() {
    Text(
        modifier = Modifier.fillMaxSize().padding(top = 20.dp),
        text = "Главная",
        textAlign = TextAlign.Center,
        fontSize = 20.sp
    )
}

@Composable
fun Screen2() {
    Text(
        modifier = Modifier.fillMaxSize().padding(top = 20.dp),
        text = "Каталог",
        textAlign = TextAlign.Center,
        fontSize = 20.sp
    )
}

@Composable
fun Screen3() {
    Text(
        modifier = Modifier.fillMaxSize().padding(top = 20.dp),
        text = "Корзина",
        textAlign = TextAlign.Center,
        fontSize = 20.sp
    )
}

@Composable
fun Screen4() {
    Text(
        modifier = Modifier.fillMaxSize().padding(top = 20.dp),
        text = "ЛК",
        textAlign = TextAlign.Center,
        fontSize = 20.sp
    )
}
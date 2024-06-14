package com.example.p6

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.p6.HelloContent
/*
class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val clicks = mutableStateOf(0)
        setContent {
            ClickCount(clicks, {clicks.value++})
        }
    }
}
*/
@Composable
fun ClickCount(i: State<Int>, lambdaCallback: () -> Unit) { // состояние, которое содержит количество кликов. функция обратного вызова, которая будет вызвана при клике на текст.
    val clickCount = i.value
    Text(
        text = "Кликов уже:+${clickCount}",
        modifier = Modifier.clickable(onClick = lambdaCallback),
        fontSize = 25.sp
    )
}
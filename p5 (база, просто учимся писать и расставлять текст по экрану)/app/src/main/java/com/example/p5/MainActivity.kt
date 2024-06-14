package com.example.p5

import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.p5.ui.theme.P5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            P5Mod1(true)
        }
    }
}


@Composable
fun P5Mod1(layout: Boolean) {
    if (layout) {
        // сохраняет состояние между перерисовками экрана.
        var maxlines by remember {
            mutableStateOf(false)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly, //равномерно распределено пространство в вертикальном направлении.
            modifier = Modifier
                .padding(50.dp)
                .width(400.dp)
                .fillMaxHeight()
                .border(1.dp, Color.Blue)
        ) {
            Text("Привет, задание Р5!", modifier = Modifier.background(color = Color.White))
            Text(
                text = "Жду задание Р6! " +
                        "Поскорее бы начать выполнять! Вот счастливо заживём тогда!!!",
                fontSize = 14.sp,
                color = Color.White,
                maxLines = when (maxlines) {
                    false -> 1
                    true -> 10
                },
                overflow = TextOverflow.Ellipsis,  // в случае переполнения текст будет обрезаться и добавляться многоточие.
                modifier = Modifier
                    .clickable { maxlines = !maxlines }
                    .background(color = Color.Blue)
                    .width(100.dp)
            )
            Text(
                "Дождусь ли ...",
                color = Color.White,
                modifier = Modifier.background(Color.Red)
            )
        }
    } else {
        var maxlines by remember {
            mutableStateOf(false)
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Привет, задание Р5!", modifier = Modifier.background(color = Color.Yellow))
            Text(
                text = "Жду задание Р6! " +
                        "Поскорее бы начать выполнять! Вот счастливо заживём тогда!!!",
                fontSize = 14.sp,
                color = Color.Black,
                maxLines = when (maxlines) {
                    false -> 1
                    true -> 10
                },
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .clickable { maxlines = !maxlines }
                    .background(color = Color.White)
                    .width(100.dp)
            )
            Text(
                "Дождусь ли ...",
                color = Color.White,
                modifier = Modifier.background(Color.Black)
            )
        }
    }
}




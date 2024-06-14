package com.example.p6

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

// Пример 2 задание 6
/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val clicks = remember{ mutableStateOf(0)}
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF5DCE8))
                    .clickable { clicks.value+=2 },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = clicks.value.toString(),
                    textAlign = TextAlign.Center,
                    fontSize = 50.sp,
                )
            }
        }
    }
}
*/


//Пример 3 задание 6
/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val clicks = remember{ mutableStateOf(0)}
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF5DCE8))
                    .clickable { clicks.value++ },
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Text(
                        text = clicks.value.toString(),
                        fontSize = 50.sp,
                        modifier = Modifier.clickable { clicks.value++ }
                    )
                    ClickCount(clicks, {clicks.value++})
                }
            }
        }
    }
}
*/
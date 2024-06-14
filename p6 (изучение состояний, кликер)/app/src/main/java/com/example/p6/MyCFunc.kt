package com.example.p6

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun MutableStateInsideComposebleWithoutRemember(checkedState: MutableState<Boolean>) {
    val checkedValue = checkedState.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFD3E4FF)),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Row(verticalAlignment = Alignment.Top) {
                Checkbox(checked = checkedValue, onCheckedChange = { value -> checkedState.value = value})
                Text("Текст...", fontSize = 18.sp)
            }
            if (checkedValue) {
                Text(text = "Ква-ква. Хрю-хрю. Гав-гав. Мяу-мяу...")
            }
        }
    }
}


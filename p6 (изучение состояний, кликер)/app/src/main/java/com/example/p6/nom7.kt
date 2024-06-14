package com.example.p6

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
/*
class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CheckboxInRow()
            //CheckboxInColumn()
        }
    }
}
*/
@Composable
fun CheckboxInRow() {
    val context = LocalContext.current // предоставляет доступ к контексту активности
    var checkedState by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Red, RoundedCornerShape(10.dp))
                .clickable {
                    if (checkedState) {
                        Toast
                            .makeText(
                                context,
                                "Чекбокс отключён (клик по строке)!",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    } else {
                        Toast
                            .makeText(
                                context,
                                "Чекбокс включён (клик по строке)!",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
                    checkedState = !checkedState
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = checkedState, onCheckedChange = {
                if (checkedState) {
                    Toast.makeText(context, "Чекбокс отключён!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Чекбокс включён!", Toast.LENGTH_SHORT).show()
                }
                checkedState = it
            })
            Text(
                text = "Нажимая на строку, регулируешь чекбокс!",
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun CheckboxInColumn() {
    val context = LocalContext.current // предоставляет доступ к контексту активности
    var checkedState by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .border(2.dp, Color.Red, RoundedCornerShape(10.dp))
                .clickable {
                    if (checkedState) {
                        Toast
                            .makeText(
                                context,
                                "Чекбокс отключён (клик по строке)!",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    } else {
                        Toast
                            .makeText(
                                context,
                                "Чекбокс включён (клик по строке)!",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
                    checkedState = !checkedState
                }
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Checkbox(checked = checkedState, onCheckedChange = {
                if (checkedState) {
                    Toast.makeText(context, "Чекбокс отключён!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Чекбокс включён!", Toast.LENGTH_SHORT).show()
                }
                checkedState = it
            })
            Text(
                text = "Нажимая на строку, регулируешь чекбокс!",
                fontSize = 18.sp
            )
        }
    }
}
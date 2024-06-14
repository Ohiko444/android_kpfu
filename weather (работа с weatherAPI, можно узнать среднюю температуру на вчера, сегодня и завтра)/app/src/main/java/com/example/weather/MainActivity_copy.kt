package com.example.weather

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weather.ui.theme.WeatherTheme
import org.json.JSONObject
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar

const val API_KEY = "0bdb66aa9f3d4165897183630241204"

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(this)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Greeting(context: Context) {
    var selectedCity by remember { mutableStateOf("Москва") }
    var selectedDate by remember { mutableStateOf("Сегодня") }
    val temperatureState = remember { mutableStateOf("Неопределённая температура...") }
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
                .padding(vertical = 150.dp, horizontal = 40.dp)
                .border(2.dp, Color.Gray, RectangleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Температура в $selectedCity\n$selectedDate\n${temperatureState.value} C",
                modifier = Modifier.padding(10.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 40.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                //Text(text = "Выберите город: ", fontSize = 16.sp)
                Box {
                    IconButton(onClick = { expanded = true }) {
                        Text(text = "Выберите город: ", fontSize = 16.sp)
                        R.drawable.baseline_keyboard_double_arrow_down_24
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        listOf("Москва", "Санкт-Петербург", "Новосибирск", "Сочи", "Казань").forEach { city ->
                            DropdownMenuItem(onClick = {
                                selectedCity = city

                            }) {
                                Text(
                                    text = city,
                                    color = Color.Black)
                            }
                        }
                    }
                }

            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Выберите дату: ", fontSize = 16.sp)
                TextButton(onClick = { selectedDate = "Вчера" }) {
                    Text(text = "Вчера")
                }
                TextButton(onClick = { selectedDate = "Сегодня" }) {
                    Text(text = "Сегодня")
                }
                TextButton(onClick = { selectedDate = "Завтра" }) {
                    Text(text = "Завтра")
                }
            }

            Button(
                onClick = {
                    getResult(selectedCity, selectedDate, temperatureState, context)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(text = "Обновить")
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getFormattedDate(date: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return date.format(formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
private fun getResult(city: String, date: String, state: MutableState<String>, context: Context) {

    var dateNow = Calendar.getInstance()

    // Определяем дату для запроса
    val whenTo = when (date) {
        "Вчера" -> {
            dateNow = Calendar.getInstance()
            dateNow.add(Calendar.DATE, -1)
            getFormattedDate(dateNow.time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
        }
        "Завтра" -> {
            dateNow = Calendar.getInstance()
            dateNow.add(Calendar.DATE, +1)
            getFormattedDate(dateNow.time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
        }
        else -> {
            getFormattedDate(Calendar.getInstance().time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
        }
    }

    val url = "https://api.weatherapi.com/v1/history.json" +
            "?key=$API_KEY" +
            "&q=$city" +
            "&dt=$whenTo"

    val queue = Volley.newRequestQueue(context)
    val stringRequest = StringRequest(
        Request.Method.GET,
        url,
        { response ->
            val obj = JSONObject(response)

            val forecastDayArray = obj.getJSONObject("forecast").getJSONArray("forecastday")

            val firstForecastDay = forecastDayArray.getJSONObject(0)

            val dayObject = firstForecastDay.getJSONObject("day")

            state.value = dayObject.getString("avgtemp_c")
        },
        { error ->
            Log.d("Sh1", "Error $error")
        }
    )
    queue.add(stringRequest)
}
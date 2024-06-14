package com.example.p10

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

object Navigation {

    @Composable
    fun MinimalNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Marshroutes.route1) {
            composable(route = Marshroutes.route1) {
                Screen1(navController = navController)
            }
            composable(route = Marshroutes.route2) {
                Screen2()
            }
            composable(route = Marshroutes.route3) {
                Screen3()
            }
            composable(route = Marshroutes.route4) {
                Screen4()
            }
            composable(route = Marshroutes.route5) {
                UDFprinciple.Screen5()
            }
            composable(route = Marshroutes.route6) {
                Screen6()
            }
            composable(route = Marshroutes.route7) {
                Screen7()
            }
        }
    }

    @Composable
    fun Screen1(navController: NavController) {
        OutlinedCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { navController.navigate(Marshroutes.route2) }) {
                    Text(text = "P10 Без ViewModel")
                }
                Button(onClick = { navController.navigate(Marshroutes.route3) }) {
                    Text(text = "P10+ C ViewModel")
                }
                Button(onClick = { navController.navigate(Marshroutes.route4) }) {
                    Text(text = "P10++ Инкапсуляция поля")
                }
                Button(onClick = { navController.navigate(Marshroutes.route5) }) {
                    Text(text = "P10+++ UDF")
                }
                Button(onClick = { navController.navigate(Marshroutes.route6) }) {
                    Text(text = "P10++++ rememberSaveable")
                }
                Button(onClick = { navController.navigate(Marshroutes.route7) }) {
                    Text(text = "Мой пример")
                }
            }
        }
    }

    // Без ViewModel
    @Composable
    fun Screen2() {
        var i by remember { mutableStateOf(0) }
        Text(
            text = "Клики: $i",
            modifier = Modifier.clickable(onClick = { i++ })
        )
    }

    // C ViewModel
    @Composable
    fun Screen3(myViewModel: MyViewModel3 = viewModel()) {
        var i by myViewModel.i // используется делегирование свойства
        Text(
            text = "Клики: $i",
            modifier = Modifier.clickable(onClick = { i++ })
        )
    }

    class MyViewModel3 :
        ViewModel() { // для сохранения и управления данными, связанными с пользовательским интерфейсом
        val i = mutableStateOf(0)
    }

    // Инкапсуляция поля
    @Composable
    fun Screen4(myViewModel: MyViewModel4 = viewModel()) {
        val i by myViewModel.i
        Text(
            text = "Клики: $i",
            modifier = Modifier.clickable(onClick = myViewModel::increment)
        )
    }

    class MyViewModel4 : ViewModel() {
        var i = mutableStateOf(0)
            private set

        fun increment() {
            i.value++
        }
    }

    // P10+++ UDF

    object UDFprinciple {
        @Composable
        fun Screen5(myViewModel: MyViewModel4 = viewModel()) {
            val i by myViewModel.i
            increment(i, myViewModel::increment)
        }

        @Composable
        fun increment(i: Int, increment: () -> Unit) {
            Text(
                text = "Клики: $i",
                modifier = Modifier.clickable(onClick = increment)
            )
        }
    }

    // P10++++ rememberSaveable
    @Composable
    fun Screen6() {
        var i by rememberSaveable { mutableStateOf(0) }
        Text(
            text = "Клики: $i",
            modifier = Modifier.clickable(onClick = { i++ })
        )
    }

    // Мой пример - секундомер
    @Composable
    fun Screen7(timerViewModel: TimerViewModel = viewModel()) {
        val isRunning by timerViewModel.isRunningState

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = formatTime(timerViewModel.elapsedTime),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Button(
                    onClick = { timerViewModel.toggleTimer() },
                    enabled = !isRunning
                ) {
                    Text(text = "Старт")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = { timerViewModel.toggleTimer() },
                    enabled = isRunning
                ) {
                    Text(text = "Пауза")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = { timerViewModel.resetTimer() }
                ) {
                    Text(text = "Сброс")
                }
            }
        }
    }

    // ViewModel для управления секундомером
    class TimerViewModel : ViewModel() {
        var isRunningState = mutableStateOf(false)
            private set

        var elapsedTime by mutableStateOf(0L) // отображает текущее время в пользовательском интерфейсе
            private set

        private var timer: Timer? = null //  управляет временем

        fun toggleTimer() {
            isRunningState.value = !isRunningState.value
            if (isRunningState.value) {
                startTimer()
            } else {
                pauseTimer()
            }
        }

        private fun startTimer() {
            timer = Timer()
            // Этот метод позволяет назначить задачу, которая будет выполняться с заданной периодичностью
            timer?.scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    elapsedTime += 1000
                }
            }, 0, 1000)
        }

        private fun pauseTimer() {
            timer?.cancel()
            timer = null
        }

        fun resetTimer() {
            elapsedTime = 0
            pauseTimer()
        }
    }

    // Функция для форматирования времени в формат "минуты:секунды"
    private fun formatTime(time: Long): String {
        val minutes = time / 60000
        val seconds = (time % 60000) / 1000
        return "%02d:%02d".format(minutes, seconds)
    }

}

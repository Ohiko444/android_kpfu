package com.example.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.navigation.ui.theme.NavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var route = remember { mutableStateOf(ScreenNames.LOGIN.name) }

            Column (modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                ) {
                    when (route.value) {
                        ScreenNames.LOGIN.name -> LoginScreen()
                        ScreenNames.GAME.name -> GameScreen()
                        ScreenNames.MAP.name -> MapScreen()
                        ScreenNames.RESULTS.name -> ResultScreen()
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    NavButton(ScreenNames.LOGIN.name, route)
                    NavButton(ScreenNames.GAME.name, route)
                    NavButton(ScreenNames.MAP.name, route)
                    NavButton(ScreenNames.RESULTS.name, route)
                }
            }
        }
    }

    @Composable
    fun NavButton(text: String, f: MutableState<String>) {
        Button(onClick = { f.value = text } ) {
            Text(text = text)
        }
    }

    @Composable
    fun LoginScreen() {
        CardScreen(ScreenNames.LOGIN.toString())
    }

    @Composable
    fun GameScreen() {
        CardScreen(ScreenNames.GAME.toString())
    }

    @Composable
    fun MapScreen() {
        CardScreen(ScreenNames.MAP.toString())
    }

    @Composable
    fun ResultScreen() {
        CardScreen(ScreenNames.RESULTS.toString())
    }

    @Composable
    fun CardScreen(screenNames: String) {
        val cardPaddingModifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
        Card(
            shape = CutCornerShape(30.dp), // создает карточку с закругленными углами и вырезом углов
            modifier = cardPaddingModifier,
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp) // так сказать тень
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(screenNames)
            }
        }
    }
}

enum class ScreenNames{
    LOGIN, GAME, MAP, RESULTS
}
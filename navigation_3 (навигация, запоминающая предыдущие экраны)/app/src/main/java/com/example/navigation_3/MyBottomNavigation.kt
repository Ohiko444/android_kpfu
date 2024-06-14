package com.example.navigation_3

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun MyBottomNavigation(navController: NavController) {
    val listItem = listOf(BottomItem.Screen1, BottomItem.Screen2, BottomItem.Screen3, BottomItem.Screen4)
    BottomNavigation(
        backgroundColor = Color.Gray
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState() // возвращает значение, которое является состоянием стека обратного вызова
        val currentRoute = backStackEntry?.destination?.route // получаем путь открытого экрана
        listItem.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route, // проверяем какой экран открыт и его нужно отметить
                onClick = {
                    navController.navigate(item.route) // при нажатии переключаем экран
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = "Icon"
                    )
                },
                label = { Text(text = item.title, fontSize = 10.sp) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.LightGray
            )
        }
    }
}
package com.example.navigation_3


sealed class BottomItem(val title: String, val iconId: Int, val route: String) {
    object Screen1: BottomItem("Главная", R.drawable.icon, "screen_1")
    object Screen2: BottomItem("Каталог", R.drawable.icon, "screen_2")
    object Screen3: BottomItem("Корзина", R.drawable.icon, "screen_3")
    object Screen4: BottomItem("ЛК", R.drawable.icon, "screen_4")
}
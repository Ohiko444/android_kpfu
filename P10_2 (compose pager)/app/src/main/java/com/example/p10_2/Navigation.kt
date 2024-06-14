package com.example.p10_2

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

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
                Screen5()
            }
            composable(route = Marshroutes.route6) {
                Screen6()
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
                    Text(text = "Без вкладок")
                }
                Button(onClick = { navController.navigate(Marshroutes.route3) }) {
                    Text(text = "С пагинацией")
                }
                Button(onClick = { navController.navigate(Marshroutes.route4) }) {
                    Text(text = "Вкладки")
                }
                Button(onClick = { navController.navigate(Marshroutes.route5) }) {
                    Text(text = "Вкладки с HorizontalPager")
                }
                Button(onClick = { navController.navigate(Marshroutes.route6) }) {
                    Text(text = "Картинки")
                }
            }
        }
    }

    // Без вкладок
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun Screen2() {
        val pagerState = rememberPagerState(pageCount = {
            10
        })
        HorizontalPager(state = pagerState) { page ->
            OutlinedCard(
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(if (page % 2 == 0) Color.DarkGray else Color.Gray)
                ) {
                    Text(
                        text = "Page: $page",
                        style = TextStyle(fontSize = 40.sp),
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }

    // С пагинацией
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun Screen3() {
        val pagerState = rememberPagerState(pageCount = {10})
        val scope = rememberCoroutineScope()

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .background(Color.LightGray)
            ) { page ->
                Text(
                    text = "Page: $page",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            Row(
                Modifier
                    .weight(0.05f)
                    .fillMaxWidth()
                    .background(Color.DarkGray),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color = if (pagerState.currentPage == iteration) Color.Gray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(2.dp, 15.dp)
                            .padding()
                            .clip(CircleShape)
                            .background(color)
                            .size(12.dp)
                            .clickable { scope.launch { pagerState.scrollToPage(iteration) } }
                    )
                }
            }
        }
    }

    // Вкладки
    object ScreenFor4 {
        @Composable
        fun SettingScreen() {
            Text(text = "Настройки", modifier = Modifier
                .fillMaxSize()
                .padding(15.dp), textAlign = TextAlign.Center, style = TextStyle(fontSize = 20.sp))
        }

        @Composable
        fun AboutScreen() {
            Text(text = "Профиль", modifier = Modifier
                .fillMaxSize()
                .padding(15.dp), textAlign = TextAlign.Center, style = TextStyle(fontSize = 20.sp))
        }

        @Composable
        fun HomeScreen() {
            Text(text = "Главная", modifier = Modifier
                .fillMaxSize()
                .padding(15.dp), textAlign = TextAlign.Center, style = TextStyle(fontSize = 20.sp))
        }

        @Composable
        fun BeScreen() {
            Text(text = "Корзина", modifier = Modifier
                .fillMaxSize()
                .padding(15.dp), textAlign = TextAlign.Center, style = TextStyle(fontSize = 20.sp))
        }
    }

    @Composable
    fun Screen4() {
        var tabIndex by remember { mutableStateOf(0) }

        val tabs = listOf("Главная", "Корзина", "Профиль", "Настройки")

        Column (modifier = Modifier.fillMaxWidth()) {
            TabRow(selectedTabIndex = tabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }

            when (tabIndex) {
                0 -> ScreenFor4.HomeScreen()
                1 -> ScreenFor4.BeScreen()
                2 -> ScreenFor4.AboutScreen()
                3 -> ScreenFor4.SettingScreen()
            }
        }
    }

    // Вкладки с HorizontalPager
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun Screen5() {
        val tabs = listOf("Главная", "Корзина", "Профиль", "Настройки")
        val pagerState = rememberPagerState(pageCount = { tabs.size })
        val selectedTabIndex by remember { derivedStateOf { pagerState.currentPage } }
        val scope = rememberCoroutineScope()

        Column(modifier = Modifier.fillMaxWidth()) {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier.fillMaxWidth()
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        onClick = { scope.launch { pagerState.scrollToPage(index) } },
                        selected = selectedTabIndex == index
                    )
                }
            }

            HorizontalPager(state = pagerState) {page ->
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Page: $page is about ${tabs[pagerState.currentPage]}",
                        modifier = Modifier.align(Alignment.Center),
                        fontSize = 40.sp,
                        fontWeight = if (page % 2 == 0) FontWeight.Bold else FontWeight.Normal,
                        color = Color.Red
                    )
                }
            }
        }
    }

    // картинки
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun Screen6() {
        val pagerState = rememberPagerState(pageCount = {
            10
        })
        HorizontalPager(state = pagerState) { page ->
            Column {

                OutlinedCard(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .width(500.dp)
                        .height(400.dp)
                        .padding(20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(if (page % 2 == 0) Color.DarkGray else Color.Gray)
                    ) {
                        AsyncImage(
                            model = randomSampleImageUrl(width = 500, height = 500),
                            contentScale = ContentScale.Fit,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        )
                    }
                }
                OutlinedCard(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .width(500.dp)
                        .height(400.dp)
                        .padding(20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(if (page % 2 == 0) Color.DarkGray else Color.Gray)
                    ) {
                        AsyncImage(
                            model = randomSampleImageUrl(width = 500, height = 500),
                            contentScale = ContentScale.Fit,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        )
                    }
                }
            }
        }
    }

    fun randomSampleImageUrl(
        seed: Int = (0..100000).random(),
        width: Int = 300,
        height: Int = width,
    ) : String {
        //return "https://picsum.photos/seed/$seed/$width/$height"
        return "https://loremflickr.com/$width/$height?random=$seed"
    }

}
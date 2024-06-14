package com.shamil.rmp.android.rooom2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import com.shamil.rmp.android.rooom2.ui.theme.Rooom2Theme
import io.github.serpro69.kfaker.Faker

/*
class MainActivity : ComponentActivity() {
    val viewModel: CityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val faker = Faker()
        viewModel.addCity(City(faker.address.city(), (-30 .. 30).random().toFloat() ))
        setContent {
            val citiz = viewModel.citiesLD.observeAsState()
            Column {
                citiz.value?.forEach {
                    Text(text = "city: "+it.name+", temp: "+it.temp)
                }
                Button(onClick = { viewModel.addCity(City(faker.address.city(), 2.0f)) }) {
                    Text(text = "Add city")
                }
            }
        }
    }
}
 */

class MainActivity : ComponentActivity() {
    val viewModel: CityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var citiz = mutableStateListOf<City>()

        val faker = Faker()
        val add: () -> Unit = {
            viewModel.addCity(
                City(
                    faker.address.city(),
                    (-30..30).random().toFloat()
                )
            )
        }

        val deleteRandom: () -> Unit = {
            viewModel.deleteCity(viewModel.citiesLD.value!!.get(0))
        }
        viewModel.citiesLD.observe(this, Observer {})
        setContent {
            viewModel.citiesLD.observe(this, Observer<List<City>>{
                citiz.clear()
                citiz.addAll(it)
            })

            showCities(citiz = citiz, add, deleteRandom)
        }
    }
}

@Composable
fun showCities(citiz: MutableList<City>, onClick: ()->Unit, deleteRandom: ()->Unit){
    LazyColumn{
        items(citiz) {
            Text(text=it.name+" "+it.temp)
        }
        item {
            Button(onClick = onClick) {
                Text("Add city")
            }
        }
        item {
            Button(onClick = deleteRandom) {
                Text("Delete random")
            }
        }
    }
}

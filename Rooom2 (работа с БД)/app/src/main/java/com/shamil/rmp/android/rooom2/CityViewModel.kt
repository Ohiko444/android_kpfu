package com.shamil.rmp.android.rooom2

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class CityViewModel: ViewModel() {
    private val cityRepository = CityRepository.get()
    val citiesLD: LiveData<List<City>> = cityRepository.getCities()
    fun addCity(city: City) {
        cityRepository.addCity(city)
    }
    fun deleteCity(city: City) {
        cityRepository.delete(city)
    }
}
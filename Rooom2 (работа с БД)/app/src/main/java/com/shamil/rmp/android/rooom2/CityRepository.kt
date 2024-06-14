package com.shamil.rmp.android.rooom2

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.lang.IllegalArgumentException
import java.util.UUID
import java.util.concurrent.Executors


private const val DB="cities"

class CityRepository private constructor(context: Context) {
    private val db: CityDatabase = Room.databaseBuilder(context.applicationContext, CityDatabase::class.java, DB).build()
    private val cityDAO: CityDAO = db.CityDAO()
    private val executor = Executors.newSingleThreadExecutor()

    fun getCities(): LiveData<List<City>> = cityDAO.getCities()
    fun getCity(id: UUID): LiveData<City?> = cityDAO.getCity(id)

    fun addCity(city: City) {
        executor.execute{
            cityDAO.addCity(city)
        }
    }

    fun delete(city: City) {
        executor.execute{
            cityDAO.delete(city)
        }
    }

    companion object {
        private var INSTANCE: CityRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CityRepository(context)
            }
        }

        fun get(): CityRepository {
            return INSTANCE ?:
            throw IllegalArgumentException("not initialized")
        }

    }

}

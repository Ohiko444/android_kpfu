package com.shamil.rmp.android.rooom2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.UUID

@Dao
interface CityDAO {
    @Query("select * from city")
    fun getCities(): LiveData<List<City>>

    @Query("select * from city where id=(:id)")
    fun getCity(id: UUID): LiveData<City?>

    @Update
    fun updateCity(city: City)

    @Query("select count(*) from city")
    fun countCities(): LiveData<Int>

    @Insert
    fun addCity(city: City)

    @Delete
    fun delete(city: City)
}
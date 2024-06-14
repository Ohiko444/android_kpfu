package com.shamil.rmp.android.rooom2

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [City::class], version = 1)
abstract class CityDatabase: RoomDatabase() {
    abstract fun CityDAO(): CityDAO
}
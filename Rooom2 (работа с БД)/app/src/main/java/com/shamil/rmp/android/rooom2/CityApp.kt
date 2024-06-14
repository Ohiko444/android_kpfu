package com.shamil.rmp.android.rooom2

import android.app.Application

class CityApp: Application() {
    override fun onCreate() {
        super.onCreate()
        CityRepository.initialize(this)
    }
}
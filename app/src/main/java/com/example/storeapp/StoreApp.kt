package com.example.storeapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class StoreApp: Application() {

    companion object {
        @JvmStatic
        lateinit var app: StoreApp
    }

    override fun onCreate() {
        super.onCreate()

        app = this
    }
}
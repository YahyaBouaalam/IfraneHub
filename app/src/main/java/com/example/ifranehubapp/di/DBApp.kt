package com.example.ifranehubapp.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DBApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
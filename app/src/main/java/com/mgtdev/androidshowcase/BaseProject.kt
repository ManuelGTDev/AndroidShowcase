package com.mgtdev.androidshowcase

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseProject : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}
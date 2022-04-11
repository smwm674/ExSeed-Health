package com.exseed.health

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LifecycleObserver

class App : Application(), LifecycleObserver {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}
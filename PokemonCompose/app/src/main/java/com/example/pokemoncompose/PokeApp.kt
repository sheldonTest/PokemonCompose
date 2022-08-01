package com.example.pokemoncompose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


/**
 * Global Hilt Entry point.
 * Also, establish Logging utility
  */
@HiltAndroidApp
class PokeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.d("Timber Has been Planted")
    }
}
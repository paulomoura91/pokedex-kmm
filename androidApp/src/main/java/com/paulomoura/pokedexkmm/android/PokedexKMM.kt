package com.paulomoura.pokedexkmm.android

import android.app.Application
import com.paulomoura.pokedexkmm.android.di.appModule
import com.paulomoura.pokedexkmm.di.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PokedexKMM : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PokedexKMM)
            modules(commonModule, appModule)
        }
    }
}
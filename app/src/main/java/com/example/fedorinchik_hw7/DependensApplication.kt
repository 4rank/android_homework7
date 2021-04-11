package com.example.fedorinchik_hw7

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import androidx.multidex.MultiDex

class DependensApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DependensApplication)
            androidLogger(Level.DEBUG)
            modules(
                mutableListOf(
                    moduleViewModel,
                    moduleRepository,
                    moduleGiphyViewModel,
                    moduleURL,
                    moduleApi
                )
            )
        }
    }

    override fun attachBaseContext(context: Context?) {
        super.attachBaseContext(context)
        MultiDex.install(this)
    }
}
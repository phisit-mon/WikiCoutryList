package com.phisit.wikicoutrylist

import android.app.Application
import android.util.Log
import com.phisit.wikicoutrylist.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        Log.e("MainApplication", "onCreate...")
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(presentationModule)
        }
    }
}
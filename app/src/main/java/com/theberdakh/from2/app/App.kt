package com.theberdakh.from2.app

import android.app.Application
import com.theberdakh.from2.di.appModule
import com.theberdakh.from2.di.networkModule
import com.theberdakh.from2.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, networkModule, viewModelModule))
        }
    }

}
package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.di.apiModule
import com.example.weatherapp.di.useCaseModule
import com.example.weatherapp.di.utilModule
import com.example.weatherapp.di.validatorModule
import com.example.weatherapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(
                viewModelModule,
                utilModule,
                useCaseModule,
                apiModule,
                validatorModule
            )
        }
    }
}

package com.example.currencyswap

import android.app.Application
import com.example.currencyswap.data.di.DataModule
import com.example.currencyswap.domain.di.DomainModule
import com.example.currencyswap.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}
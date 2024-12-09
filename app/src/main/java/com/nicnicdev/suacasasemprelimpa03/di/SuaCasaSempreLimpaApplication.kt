package com.nicnicdev.suacasasemprelimpa03.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SuaCasaSempreLimpaApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initializaKoin()
    }
    private fun initializaKoin() {
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@SuaCasaSempreLimpaApplication)

            modules(
                networkModule,
            )
        }
    }
}
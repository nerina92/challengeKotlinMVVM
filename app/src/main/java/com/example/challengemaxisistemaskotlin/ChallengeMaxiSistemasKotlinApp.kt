package com.example.challengemaxisistemaskotlin

import android.app.Application
import com.example.challengemaxisistemaskotlin.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class ChallengeMaxiSistemasKotlinApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ChallengeMaxiSistemasKotlinApp)
            modules(appModules)
        }
    }
}
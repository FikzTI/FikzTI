package org.chevalierlabsas.kashier12

import android.app.Application
import org.chevalierlabsas.Kashier.core.di.shareModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
// import org.chevalierlabsas.Kashier.core.di.appModule

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)

            modules(shareModules)
        }
    }
}
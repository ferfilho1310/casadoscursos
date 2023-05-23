package br.com.casadoscursos.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CasaDosCursosApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CasaDosCursosApplication)
            androidLogger(Level.NONE)
            modules(
                listOf(
                    CasaDosCursosModule.instance
                )
            )
        }
    }
}
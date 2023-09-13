package br.com.casadoscursos.di

import android.app.Application
import android.util.Log
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener

const val KEY_APPS_FLYER = "fgtKPTELriXxoF2RGqe8WW"

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
        initAppsFlyer()
        initListenerAppsFlyer()
        debugModeAppsFlyer()
    }

    private fun debugModeAppsFlyer() {
        AppsFlyerLib.getInstance().setDebugLog(true);
    }
    private fun initAppsFlyer() {
        AppsFlyerLib.getInstance().init(KEY_APPS_FLYER, null, this)
        AppsFlyerLib.getInstance().start(this)
    }

    private fun initListenerAppsFlyer() {
        AppsFlyerLib.getInstance().start(this, KEY_APPS_FLYER, object : AppsFlyerRequestListener {
            override fun onSuccess() {
                Log.d("LOG_TAG", "Launch sent successfully")
            }

            override fun onError(errorCode: Int, errorDesc: String) {
                Log.d("LOG_TAG", "Launch failed to be sent:\n" +
                        "Error code: " + errorCode + "\n"
                        + "Error description: " + errorDesc)
            }
        })
    }
}
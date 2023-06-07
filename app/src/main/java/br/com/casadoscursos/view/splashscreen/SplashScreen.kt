package br.com.casadoscursos.view.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.casadoscursos.R
import br.com.casadoscursos.view.onboarding.view.OnboardingActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorStatusBar)

        val hSplash = Handler()
        hSplash.postDelayed(
            {
                val iSplash = Intent(this, OnboardingActivity::class.java)
                startActivity(iSplash)
                finish()
            },
            3000
        )
    }
}
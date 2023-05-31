package br.com.casadoscursos.view.onboarding.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import br.com.casadoscursos.R
import br.com.casadoscursos.databinding.ActivityOnboardingBinding
import br.com.casadoscursos.models.OnboardingItem
import br.com.casadoscursos.view.activity.CursosMaintActivity
import br.com.casadoscursos.view.onboarding.adapter.OnboardingItemsAdapter

class OnboardingActivity : AppCompatActivity(), View.OnClickListener {

    private val adsAdapter by lazy { OnboardingItemsAdapter() }
    private lateinit var indicatorContainer: LinearLayout
    private lateinit var sharedPreferences: SharedPreferences

    private val binding by lazy { ActivityOnboardingBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        sharedPreferences = getSharedPreferences(SKIPPED.SKIPPED_ONBOARDING, Context.MODE_PRIVATE)

        setOnboardingItems()
        setupIndicators()

        binding.btOnboading.setOnClickListener(this)
        binding.skipped.setOnClickListener(this)
    }

    private fun setOnboardingItems() {
        adsAdapter.setItemOnboarding(
            arrayListOf(
                OnboardingItem(
                    title = "Vários cursos relacionados a internet e a linguas para atender às necessidades de aprendizagem de todos.",
                    image = R.raw.educacao
                ),
                OnboardingItem(
                    title = "Explore nosso catálogo de cursos e aprimore suas habilidades em maquiagem, penteados e muito mais.",
                    image = R.raw.beleza
                )
            )
        )

        val viewPage = findViewById<ViewPager2>(R.id.onboardingViewPager)
        viewPage.adapter = adsAdapter
        viewPage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setupCurrentIndicator(position)
            }
        })
        (viewPage.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }

    private fun setupIndicators() {
        indicatorContainer = findViewById(R.id.indicatoContainer)
        val indicators = arrayOfNulls<ImageView>(adsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                indicatorContainer.addView(it)
            }
        }
    }

    private fun setupCurrentIndicator(position: Int) {
        val childCount = indicatorContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorContainer.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_activebackground
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.bt_onboading -> {
                preferencesSkipped()
                startMainActivity()
            }

            R.id.skipped -> {
                preferencesSkipped()
                startMainActivity()
            }
        }
    }

    private fun startMainActivity() {
        val i = Intent(this, CursosMaintActivity::class.java)
        startActivity(i)
        finish()
    }

    private fun preferencesSkipped() {
        val result = sharedPreferences.edit()
        result.putBoolean("skipped", true)
        result.apply()
    }

    object SKIPPED {
        const val SKIPPED_ONBOARDING = "SKIPPED"
    }

    override fun onStart() {
        super.onStart()
        if (sharedPreferences.getBoolean("skipped", false)) {
            startActivity(
                Intent(
                    this,
                    CursosMaintActivity::class.java
                )
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
            finish()
        }
    }
}
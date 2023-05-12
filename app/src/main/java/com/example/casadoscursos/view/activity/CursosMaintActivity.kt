package com.example.casadoscursos.view.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.casadoscursos.view.activity.adapter.CursosAdapter
import com.example.casadoscursos.databinding.CursosMainActivityBinding
import com.example.casadoscursos.helpers.Response
import com.example.casadoscursos.viewModels.RemoteConfigViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class CursosMaintActivity : AppCompatActivity() {

    private val binding by lazy {
        CursosMainActivityBinding.inflate(layoutInflater)
    }
    private val cursosAdapter by lazy {
        CursosAdapter(supportFragmentManager, lifecycle)
    }

    val titles = arrayListOf("Beleza", "Culinária", "Educação", "Saúde")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.viewPager.adapter = cursosAdapter
        binding.apply {
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = titles[position]
            }.attach()
        }

    }
}

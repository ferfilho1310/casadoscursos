package com.example.casadoscursos.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.casadoscursos.adapter.CursosAdapter
import com.example.casadoscursos.databinding.CursosMainActivityBinding
import com.google.android.material.tabs.TabLayoutMediator

class CursosMaintActivity : AppCompatActivity() {

    private val binding by lazy {
        CursosMainActivityBinding.inflate(layoutInflater)
    }
    private val cursosAdapter by lazy {
        CursosAdapter(supportFragmentManager, lifecycle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setAdapter()
        setTabLayoutMediator()
    }

    private fun setTabLayoutMediator() {
        binding.apply {
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                //tab.text = animalsArray[position]
            }.attach()
        }
    }

    private fun setAdapter() {
        binding.apply {
            viewPager.adapter = cursosAdapter
        }
    }
}
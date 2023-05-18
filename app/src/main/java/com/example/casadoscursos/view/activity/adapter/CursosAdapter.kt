package com.example.casadoscursos.view.activity.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.casadoscursos.view.fragments.beatifullCursos.BelezaFragment
import com.example.casadoscursos.view.fragments.culinariaCursos.CulinariaFragment
import com.example.casadoscursos.view.fragments.educacaoCursos.EducacaoFragment
import com.example.casadoscursos.view.fragments.saudeCursos.SaudeFragment

class CursosAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
       return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> EducacaoFragment.newInstance()
            1 -> CulinariaFragment.newInstance()
            2 -> BelezaFragment.newInstance()
            3 -> SaudeFragment.newInstance()
            else ->  throw IllegalArgumentException("Invalid position $position")
        }
    }
}
package br.com.casadoscursos.view.activity.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.casadoscursos.view.fragments.beatifullcursos.BelezaFragment
import br.com.casadoscursos.view.fragments.culinariacursos.CulinariaFragment
import br.com.casadoscursos.view.fragments.destaques.DestaquesFragment
import br.com.casadoscursos.view.fragments.educacaoCursos.EducacaoFragment
import br.com.casadoscursos.view.fragments.saudeCursos.SaudeFragment

class CursosAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DestaquesFragment.newInstance()
            1 -> BelezaFragment.newInstance()
            2 -> CulinariaFragment.newInstance()
            3 -> EducacaoFragment.newInstance()
            4 -> SaudeFragment.newInstance()
            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }
}
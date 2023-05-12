package com.example.casadoscursos.view.activity.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.casadoscursos.view.fragments.beatifullCursos.BeatifulTitleFragment

class CursosAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
       return 1
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BeatifulTitleFragment.newInstance()
            else ->  throw IllegalArgumentException("Invalid position $position")
        }
    }
}
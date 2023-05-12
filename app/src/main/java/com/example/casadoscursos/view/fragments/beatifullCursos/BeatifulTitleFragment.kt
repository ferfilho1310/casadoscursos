package com.example.casadoscursos.view.fragments.beatifullCursos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.casadoscursos.databinding.BeatifulCategoryFragmentBinding
import com.example.casadoscursos.helpers.Response
import com.example.casadoscursos.models.Cursos
import com.example.casadoscursos.view.adapterCursos.AdapterCursos
import com.example.casadoscursos.viewModels.RemoteConfigViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BeatifulTitleFragment : Fragment() {

    private var _binding: BeatifulCategoryFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: RemoteConfigViewModel by viewModel()
    private val adapterCursos by lazy {
        AdapterCursos()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BeatifulCategoryFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
    }

    override fun onStart() {
        super.onStart()
        viewmodel.remoteConfigFetch(context, PARAMENTRO_BEATIFUL)
    }

    private fun setViewModel() {
        viewmodel.remoteconfig.observe(viewLifecycleOwner) {
            when (it) {
                is Response.LOADING -> {

                }
                is Response.SUCCESS -> {
                    binding.rcBeatifulCategory.apply {
                        adapter = adapterCursos
                        setHasFixedSize(true)
                    }

                    adapterCursos.setCursos(requireContext(),it.data)
                }
                else -> Unit
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val PARAMENTRO_BEATIFUL = "beleza"

        fun newInstance(): BeatifulTitleFragment {
            return BeatifulTitleFragment()
        }
    }
}
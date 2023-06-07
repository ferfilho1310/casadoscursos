package br.com.casadoscursos.view.fragments.beatifullcursos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import br.com.casadoscursos.databinding.CursoFragmentBinding
import br.com.casadoscursos.helpers.Response
import br.com.casadoscursos.view.adapterCursos.AdapterCursos
import br.com.casadoscursos.view.fragments.cursonavigatebottomsheet.CursoInformationNavigateBottomSheet
import br.com.casadoscursos.viewModels.RemoteConfigViewModel
import com.google.android.gms.ads.AdRequest
import org.koin.androidx.viewmodel.ext.android.viewModel

class BelezaFragment : Fragment() {

    private var _binding: CursoFragmentBinding? = null
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
        _binding = CursoFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.remoteConfigFetch(context, PARAMENTRO_BEATIFUL)
        setViewModel()
        loadAds()
        setSwipeRefreshLayoutListener()
    }

    private fun setSwipeRefreshLayoutListener() {
        binding.swipe.setOnRefreshListener {
            viewmodel.remoteConfigFetch(context, PARAMENTRO_BEATIFUL)
            binding.swipe.isRefreshing = false
        }
    }

    private fun setViewModel() {
        viewmodel.remoteconfig.observe(viewLifecycleOwner) {
            when (it) {
                is Response.LOADING -> {
                    binding.apply {
                        rcBeatifulCategory.isVisible = false
                        pgCurso.isVisible = true
                    }
                }

                is Response.SUCCESS -> {
                    binding.apply {
                        rcBeatifulCategory.isVisible = true
                        pgCurso.isVisible = false
                    }

                    binding.rcBeatifulCategory.apply {
                        adapter = adapterCursos
                        setHasFixedSize(true)
                    }

                    adapterCursos.setCursos(
                        requireContext(),
                        it.data,
                        object : AdapterCursos.CursoListener {
                            override fun onClickCurso(urlAffiliate: String) {
                                val bottomSheet = CursoInformationNavigateBottomSheet(urlAffiliate)
                                bottomSheet.show(childFragmentManager, "TAG")
                            }
                        })
                }

                else -> Unit
            }
        }
    }

    private fun loadAds() {
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val PARAMENTRO_BEATIFUL = "beleza"

        fun newInstance(): BelezaFragment {
            return BelezaFragment()
        }
    }
}
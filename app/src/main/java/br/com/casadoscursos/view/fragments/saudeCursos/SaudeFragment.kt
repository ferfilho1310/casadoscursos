package br.com.casadoscursos.view.fragments.saudeCursos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import br.com.casadoscursos.databinding.CursoFragmentBinding
import br.com.casadoscursos.helpers.Response
import br.com.casadoscursos.view.adapterCursos.AdapterCursos
import br.com.casadoscursos.viewModels.RemoteConfigViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SaudeFragment : Fragment() {

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
        viewmodel.remoteConfigFetchBemEstar(context, PARAMENTRO_SAUDE)
        setViewModel()
    }

    private fun setViewModel() {
        viewmodel.remoteconfigBemEstar.observe(viewLifecycleOwner) {
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
                                sendPageWeb(urlAffiliate)
                            }
                        })
                }

                else -> Unit
            }
        }
    }

    private fun sendPageWeb(urlAfiliate: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(urlAfiliate.trim()))
        startActivity(browserIntent)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val PARAMENTRO_SAUDE = "saude"

        fun newInstance(): SaudeFragment {
            return SaudeFragment()
        }
    }
}
package br.com.casadoscursos.view.fragments.destaques

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.casadoscursos.databinding.DestaquesFragmentBinding
import br.com.casadoscursos.helpers.Response
import br.com.casadoscursos.viewModels.carrosselcourses.CarrosselCoursesDestaquesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class DestaquesFragment : Fragment() {

    private var _binding: DestaquesFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CarrosselCoursesDestaquesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DestaquesFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.searchCourseCarrossel()
        setViewModelCarrosel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setViewModelCarrosel() {
        viewModel.carrosselcourses.observe(viewLifecycleOwner) {
            when (it) {
                is Response.SUCCESS -> {
                    it.data?.let { cursos ->
                        binding.destaqueCarrosselView.setOnboardingItems(cursos) { urlAffiliate ->
                            sendPageWeb(urlAffiliate)
                        }
                    }
                }

                is Response.ERROR -> {
                    Log.e("Error", "Erro ao carregar o carrossel")
                }

                else -> Unit
            }
        }

    }

    private fun sendPageWeb(urlAfiliate: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(urlAfiliate.trim()))
        startActivity(browserIntent)
    }

    companion object {
        fun newInstance() =
            DestaquesFragment()
    }
}
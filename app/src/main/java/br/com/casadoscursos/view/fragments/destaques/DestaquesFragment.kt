package br.com.casadoscursos.view.fragments.destaques

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import br.com.casadoscursos.databinding.DestaquesFragmentBinding
import br.com.casadoscursos.helpers.Response
import br.com.casadoscursos.models.Cursos
import br.com.casadoscursos.view.activity.DetailsCoursesActivity
import br.com.casadoscursos.viewModels.carrosselcourses.CarrosselCoursesDestaquesViewModel
import com.google.android.gms.ads.AdRequest
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

        viewModel.apply {
            searchDestaqueCarrossel(DESTAQUESBEMESTAR)
            searchDestaqueCarrosselBeleza(DESTAQUESBELEZA)
            searchDestaqueCarrosselCulinaria(DESTAQUESCULINARIA)
            searchDestaqueCarrosselEducacao(DESTAQUESEDUCACAO)
        }

        setViewModelBemEstar()
        setViewModelBeleza()
        setViewModelCulinaria()
        setViewModelEducacao()
        loadAds()
        accessAreaCourses()
        accessDuvidasAcesso()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun accessAreaCourses() {
        binding.cardviewMeusCursos.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW, Uri.parse("http://purchase.hotmart.com")
            )
            startActivity(browserIntent)
        }
    }

    private fun accessDuvidasAcesso() {
        binding.cardviewDuvidas.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://help.hotmart.com/pt-BR/article/como-acessar-o-produto-que-comprei-/215827338")
            )
            startActivity(browserIntent)
        }
    }

    private fun setViewModelBemEstar() {
        binding.apply {
            pgCursoDestaque.isVisible = true
            nsvDestaque.isVisible = false
        }

        viewModel.carrosselcourses.observe(viewLifecycleOwner) {
            when (it) {
                is Response.SUCCESS -> {
                    binding.apply {
                        pgCursoDestaque.isVisible = false
                        nsvDestaque.isVisible = true
                    }

                    binding.destaqueCarrosselView.setTextDestaqueCursos("Top Cursos - Bem estar")
                    it.data?.let { cursos ->
                        binding.destaqueCarrosselView.setOnboardingItems(cursos) { curso ->
                            navigateDetailsCourses(curso)
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

    private fun navigateDetailsCourses(curso: Cursos.Curso) {
        val intent = Intent(
            requireContext(),
            DetailsCoursesActivity::class.java
        )
        intent.putExtra("curso", curso)
        startActivity(intent)
    }

    private fun setViewModelEducacao() {
        viewModel.destaqueEducacao.observe(viewLifecycleOwner) {
            when (it) {
                is Response.SUCCESS -> {
                    binding.destaqueCarrosselEducacaoView.setTextDestaqueCursos("Top Cursos - Educação")
                    it.data?.let { cursos ->
                        binding.destaqueCarrosselEducacaoView.setOnboardingItems(cursos) { curso ->
                            navigateDetailsCourses(curso)
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

    private fun setViewModelBeleza() {
        viewModel.destaqueBeleza.observe(viewLifecycleOwner) {
            when (it) {
                is Response.SUCCESS -> {
                    binding.destaqueCarrosselBelezaView.setTextDestaqueCursos("Top Cursos - Beleza")
                    it.data?.let { cursos ->
                        binding.destaqueCarrosselBelezaView.setOnboardingItems(cursos) { curso ->
                            navigateDetailsCourses(curso)
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

    private fun setViewModelCulinaria() {
        viewModel.destaqueCulinaria.observe(viewLifecycleOwner) {
            when (it) {
                is Response.SUCCESS -> {
                    binding.destaqueCarrosselCulinariaView.setTextDestaqueCursos("Top Cursos - Culinaria")
                    it.data?.let { cursos ->
                        binding.destaqueCarrosselCulinariaView.setOnboardingItems(cursos) { curso ->
                            navigateDetailsCourses(curso)
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

    private fun loadAds() {
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }

    companion object {
        const val DESTAQUESBEMESTAR = "carrosseldestaques"
        const val DESTAQUESCULINARIA = "destaquesculinaria"
        const val DESTAQUESEDUCACAO = "destaqueseducacao"
        const val DESTAQUESBELEZA = "destaquesbeleza"

        fun newInstance() =
            DestaquesFragment()
    }
}
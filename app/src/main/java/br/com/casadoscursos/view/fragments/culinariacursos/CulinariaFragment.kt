package br.com.casadoscursos.view.fragments.culinariacursos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import br.com.casadoscursos.databinding.CursoFragmentBinding
import br.com.casadoscursos.helpers.Response
import br.com.casadoscursos.models.Cursos
import br.com.casadoscursos.repository.monitoringclickusers.CoursesMonitoring
import br.com.casadoscursos.view.adapterCursos.AdapterCursos
import br.com.casadoscursos.view.fragments.cursonavigatebottomsheet.CursoInformationNavigateBottomSheet
import br.com.casadoscursos.viewModels.SearchCoursesViewModel
import com.google.android.gms.ads.AdRequest
import org.koin.androidx.viewmodel.ext.android.viewModel

class CulinariaFragment : Fragment() {

    private var _binding: CursoFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: SearchCoursesViewModel by viewModel()
    private val monitoring by lazy {
        CoursesMonitoring()
    }

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
        viewmodel.searchCoursesCulinaria(context, PARAMENTRO_CULINARIA)
        setViewModel()
        loadAds()
        setSwipeRefreshLayoutListener()
    }

    private fun setSwipeRefreshLayoutListener() {
        binding.swipe.setOnRefreshListener {
            viewmodel.searchCoursesCulinaria(context, PARAMENTRO_CULINARIA)
            binding.swipe.isRefreshing = false
        }
    }

    private fun loadAds() {
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }

    private fun setViewModel() {
        viewmodel.searchCoursesCulinaria.observe(viewLifecycleOwner) {
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
                            override fun onClickCurso(curso: Cursos.Curso) {
                                val bottomSheet = CursoInformationNavigateBottomSheet(curso.linkCurso.orEmpty())
                                bottomSheet.show(childFragmentManager, "TAG")
                            }

                            override fun monitoringClick(curso: Cursos.Curso) {
                                val clickCourses = StringBuilder()
                                clickCourses.append(curso.linkCurso.orEmpty())

                                monitoring.monitoring(clickCourses.toString(), requireContext())
                            }
                        })
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
        const val PARAMENTRO_CULINARIA = "culinaria"

        fun newInstance(): CulinariaFragment {
            return CulinariaFragment()
        }
    }

}
package br.com.casadoscursos.view.fragments.destaques

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import br.com.casadoscursos.R
import br.com.casadoscursos.databinding.DestaquesFragmentBinding
import br.com.casadoscursos.helpers.Response
import br.com.casadoscursos.models.Cursos
import br.com.casadoscursos.view.fragments.destaques.carrossel.DestaquesCarroesselAdapter
import br.com.casadoscursos.viewModels.carrosselcourses.CarrosselCoursesDestaquesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DestaquesFragment : Fragment() {

    private var _binding: DestaquesFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CarrosselCoursesDestaquesViewModel by viewModel()
    private val destaqueCarrolssel by lazy { DestaquesCarroesselAdapter() }

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
                        setOnboardingItems(cursos) { urlAffiliate ->
                            sendPageWeb(urlAffiliate)
                        }
                    }
                    setupIndicators()
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

    fun setOnboardingItems(
        cursos: ArrayList<Cursos.Curso>,
        listener: DestaquesCarroesselAdapter.CarrosselListener
    ) {
        destaqueCarrolssel.setItemsCarroessel(cursos, listener)

        binding.onboardingViewPager.apply {
            adapter = destaqueCarrolssel
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setupCurrentIndicator(position)
                }
            })
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
    }

    private fun setupCurrentIndicator(position: Int) {
        val childCount = binding.indicatoContainer.childCount
        for (i in 0 until childCount) {
            val imageView = binding.indicatoContainer.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_activebackground
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }

    private fun setupIndicators() {
        binding.indicatoContainer.apply {
            val indicators = arrayOfNulls<ImageView>(destaqueCarrolssel.itemCount)
            val layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(8, 0, 8, 0)
            for (i in indicators.indices) {
                indicators[i] = ImageView(context)
                indicators[i]?.let {
                    it.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.indicator_inactive_background
                        )
                    )
                    it.layoutParams = layoutParams
                    addView(it)
                }
            }
        }
    }

    companion object {
        fun newInstance() =
            DestaquesFragment()
    }
}
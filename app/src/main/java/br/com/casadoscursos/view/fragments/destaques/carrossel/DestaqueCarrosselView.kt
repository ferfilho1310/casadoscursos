package br.com.casadoscursos.view.fragments.destaques.carrossel

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.casadoscursos.databinding.DestaqueCarrosselViewBinding
import br.com.casadoscursos.models.Cursos

class DestaqueCarrosselView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet,
    def: Int = 0
) : LinearLayout(context, attributeSet, def) {

    private val binding = DestaqueCarrosselViewBinding.inflate(LayoutInflater.from(context), this)
    private val destaqueCarrolssel by lazy { DestaquesCarroesselAdapter() }

    init {
        orientation = VERTICAL
    }

    fun setTextDestaqueCursos(textDestaque: String) {
        binding.tvTitleCursosDestaque.text = textDestaque
    }

    fun setOnboardingItems(
        cursos: ArrayList<Cursos.Curso>,
        listener: DestaquesCarroesselAdapter.CarrosselListener
    ) {
        binding.rcTopCursos.apply {
            adapter = destaqueCarrolssel
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            val dividerItemDecoration = DividerItemDecoration(
                context,
                StaggeredGridLayoutManager.HORIZONTAL
            )
            addItemDecoration(dividerItemDecoration)
        }
        destaqueCarrolssel.setItemsCarroessel(cursos, listener, context)
    }
}
package br.com.casadoscursos.view.fragments.destaques.carrossel

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import br.com.casadoscursos.databinding.DestaqueCarrosselViewBinding
import br.com.casadoscursos.models.Cursos

class DestaqueCarrosselView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet,
    def: Int = 0
) : LinearLayout(context, attributeSet, def) {

    val binding = DestaqueCarrosselViewBinding.inflate(LayoutInflater.from(context), this)
    private val destaqueCarrolssel by lazy { DestaquesCarroesselAdapter() }

    init {
        orientation = VERTICAL
    }

    fun setOnboardingItems(
        cursos: ArrayList<Cursos.Curso>,
        listener: DestaquesCarroesselAdapter.CarrosselListener
    ) {
        binding.rcTopCursos.apply {
            adapter = destaqueCarrolssel
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            val dividerItemDecoration = DividerItemDecoration(
                context,
                GridLayoutManager.HORIZONTAL
            )
            addItemDecoration(dividerItemDecoration)
        }
        destaqueCarrolssel.setItemsCarroessel(cursos, listener)
    }
}
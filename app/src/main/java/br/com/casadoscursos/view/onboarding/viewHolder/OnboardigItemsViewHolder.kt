package br.com.casadoscursos.view.onboarding.viewHolder

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.casadoscursos.R


class OnboardigItemsViewHolder(
    val view: View, val context: Context
    ) : RecyclerView.ViewHolder(view) {

    private val image = view.findViewById<ImageView>(R.id.img_onboarding)

    fun bind(onboardingItem: Int) {
        image.setImageResource(onboardingItem)
    }
}
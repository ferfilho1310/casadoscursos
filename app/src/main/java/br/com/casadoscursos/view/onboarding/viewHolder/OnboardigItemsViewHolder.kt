package br.com.casadoscursos.view.onboarding.viewHolder

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.casadoscursos.R
import br.com.casadoscursos.models.OnboardingItem
import com.airbnb.lottie.LottieAnimationView

class OnboardigItemsViewHolder(
    val view: View,
    val context: Context
) : RecyclerView.ViewHolder(view) {

    private val textOnboarding = view.findViewById<TextView>(R.id.tv_title)
    private val lottie = view.findViewById<LottieAnimationView>(R.id.lottie)

    fun bind(onboardingItem: OnboardingItem) {
        lottie.setAnimation(onboardingItem.image)
        textOnboarding.text = onboardingItem.title
    }
}
package br.com.casadoscursos.view.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.casadoscursos.R
import br.com.casadoscursos.view.onboarding.viewHolder.OnboardigItemsViewHolder

class OnboardingItemsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val onboardingItem: ArrayList<Int> = arrayListOf()

    fun setItemOnboarding(onboardingItemList: ArrayList<Int>) {
        onboardingItem.addAll(onboardingItemList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return OnboardigItemsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.onboarding_item_container,
                    parent,
                    false
                ),
            parent.context
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holderItems = holder as OnboardigItemsViewHolder
        holderItems.bind(onboardingItem[position])
    }

    override fun getItemCount(): Int {
        return onboardingItem.size
    }
}
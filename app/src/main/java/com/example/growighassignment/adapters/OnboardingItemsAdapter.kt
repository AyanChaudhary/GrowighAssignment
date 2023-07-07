package com.example.growighassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.growighassignment.Models.OnBoardingItem
import com.example.growighassignment.databinding.OnboardingItemLayoutBinding

class OnboardingItemsAdapter(private val items : List<OnBoardingItem>) :
    RecyclerView.Adapter<OnboardingItemsAdapter.ItemsViewHolder>() {

    inner class ItemsViewHolder(val binding : OnboardingItemLayoutBinding):ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(OnboardingItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val item=items[position]
        holder.binding.apply {
            ivOnboardingImage.setImageResource(item.image)
            tvOnboardingTitle.text=item.title
        }

    }
}
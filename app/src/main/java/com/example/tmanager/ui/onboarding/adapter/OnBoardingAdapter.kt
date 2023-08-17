package com.example.tmanager.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.tmanager.databinding.ItemOnboardingBinding
import com.example.tmanager.model.OnBoarding
import com.example.tmanager.ui.task.loadImage

class OnBoardingAdapter(private val onClick: () -> Unit) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val list = arrayListOf(
        OnBoarding(title = "Test1", description = "desc 1", image = ""),
        OnBoarding(title = "Test2", description = "desc 2", image = ""),
        OnBoarding(title = "Test3", description = "desc 3", image = ""),
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardingBinding.inflate
                (
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        ViewHolder(binding.root) {
        fun bind(onBoarding: OnBoarding) {
            binding.tvTitle.text = onBoarding.title
            binding.tvDesc.text = onBoarding.description
            onBoarding.image?.let { binding.imgBoard.loadImage(it) }
            binding.btnStart.isVisible = adapterPosition == list.lastIndex
            binding.skip.isVisible = adapterPosition != list.lastIndex
            binding.btnStart.setOnClickListener {
                onClick()
            }
            binding.skip.setOnClickListener {
                onClick()
            }
        }
    }
}

package com.example.tmanager.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tmanager.R
import com.example.tmanager.databinding.ItemOnboardingBinding
import com.example.tmanager.model.OnBoarding


class OnBoardingAdapter(private val onClick: () -> Unit) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val list = arrayListOf<OnBoarding>(
        OnBoarding(
            "Много возможностей",
            "Lorem ipsum dolor sit amet, cosectetur adipiscing",
            R.raw.anim_lottie_1
        ),
        OnBoarding(
            "Всегда рядом",
            "Lorem ipsum dolor sit amet, cosectetur adipiscing",
            R.raw.anim_lottie_2
        ),
        OnBoarding(
            "Удобное поьзование",
            "Lorem ipsum dolor sit amet, cosectetur adipiscing",
            R.raw.anim_lottie_3
        ),
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
            onBoarding.anim?.let {
                binding.animBoard.setAnimation(onBoarding.anim)
                binding.animBoard.playAnimation()
            }
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

package com.example.growighassignment.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.growighassignment.Models.OnBoardingItem
import com.example.growighassignment.R
import com.example.growighassignment.adapters.OnboardingItemsAdapter
import com.example.growighassignment.databinding.FragmentHomeBinding
import com.example.growighassignment.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {

    private lateinit var binding: FragmentOnboardingBinding
    private lateinit var onboardingItemsAdapter : OnboardingItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding= FragmentOnboardingBinding.bind(view)
        if(onBoardingIsFinished())findNavController().navigate(R.id.action_onboardingFragment_to_homeFragment)
        binding.tvSkipBotton.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_homeFragment)
        }

        binding.firstfl.setOnClickListener {
            if(binding.onboardingViewPager.currentItem+1<onboardingItemsAdapter.itemCount){
                binding.onboardingViewPager.currentItem+=1
                binding.progressCircular.progress+=25
            }else{
                findNavController().navigate(R.id.action_onboardingFragment_to_homeFragment)
            }
        }
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val items= listOf<OnBoardingItem>(
            OnBoardingItem(
                R.drawable.img_1,
                "About Us"
            ),
            OnBoardingItem(
                R.drawable.img_2,
                "Our Mission"
            ),
            OnBoardingItem(
                R.drawable.img_3,
                "Our Vision"
            )
        )
        onboardingItemsAdapter=OnboardingItemsAdapter(items)
        binding.onboardingViewPager.apply {
            adapter=onboardingItemsAdapter
        }
        binding.onboardingViewPager.isUserInputEnabled=false
    }
    private fun onBoardingIsFinished():Boolean{
        val sharedPreferences=requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("finished",false)
    }
}
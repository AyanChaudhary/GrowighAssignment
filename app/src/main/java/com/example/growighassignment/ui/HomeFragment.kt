package com.example.growighassignment.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.growighassignment.R
import com.example.growighassignment.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHomeBinding.bind(view)
        onBoardingIsFinished()
        binding.btnFeeds.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_feedsFragment) }
        binding.btnUploadImage.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_uploadImageFragment) }
    }

    private fun onBoardingIsFinished(){
        val sharedPreferences=requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        editor.putBoolean("finished",true)
        editor.apply()
    }
}
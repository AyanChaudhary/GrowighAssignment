package com.example.growighassignment.ui

import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.growighassignment.R
import com.example.growighassignment.databinding.FragmentUploadImageBinding

class UploadImageFragment : Fragment(R.layout.fragment_upload_image) {

    private lateinit var binding : FragmentUploadImageBinding

    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()){
        binding.ivPhoto.setImageURI(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentUploadImageBinding.bind(view)

        binding.btnSelectImage.setOnClickListener {
            contract.launch("image/*")
        }
        binding.btnCancel.setOnClickListener{
            binding.ivPhoto.setImageResource(R.drawable.img)
        }

    }

}
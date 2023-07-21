package com.example.growighassignment.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.growighassignment.Models.VideoModel
import com.example.growighassignment.R
import com.example.growighassignment.adapters.VideoAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class VideosFragment : Fragment(R.layout.fragment_videos) {

    lateinit var viewPager2: ViewPager2
    lateinit var adapter: VideoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager2 = view.findViewById(R.id.view_pager)
        val dataBase = Firebase.database.getReference("videos")
        val options = FirebaseRecyclerOptions.Builder<VideoModel>()
            .setQuery(dataBase,VideoModel::class.java)
            .build()

        /*** set daapter ****/
        adapter = VideoAdapter(options)
        viewPager2.adapter = adapter
    }
    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}
package com.example.growighassignment.repository

import com.example.growighassignment.api.RetrofitInstance

class ItemRepository {

    suspend fun getBreakingNews(country:String,page:Int)=
        RetrofitInstance.api.getBreakingNews(country,page)
}
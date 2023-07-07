package com.example.growighassignment.api

import com.example.growighassignment.Models.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY="7b192c8f07604b2eb215585a3c47cfb6"
interface ItemAPI {

    @GET("v2/top-headlines?apiKey=$API_KEY")
    suspend fun getBreakingNews(@Query("country")country:String="in",@Query("page")pageNumber:Int=1):Response<News>
}
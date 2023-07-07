package com.example.growighassignment.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.growighassignment.Models.News
import com.example.growighassignment.Util.Resource
import com.example.growighassignment.repository.ItemRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ItemViewModel(val repository : ItemRepository) : ViewModel() {

    val breakingNews:MutableLiveData<Resource<News>> = MutableLiveData()
    var breakingNewsPageNumber=1
    var breakingNewsResponse : News?=null

    init {
        getBreakingNews("in")

    }
    fun getBreakingNews(country:String)=viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response=repository.getBreakingNews(country,breakingNewsPageNumber)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<News>) : Resource<News>{
        if(response.isSuccessful){
            response.body()?.let { resultResponse->
                breakingNewsPageNumber++
                if(breakingNewsResponse==null)breakingNewsResponse=resultResponse
                else {
                    var oldArticles=breakingNewsResponse?.articles
                    val newArticles=resultResponse.articles
                    oldArticles?.addAll(newArticles)
                    breakingNewsResponse?.articles=oldArticles!!
                }
                return Resource.Success(breakingNewsResponse?:resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}

package com.dicoding.caloritrack.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.caloritrack.remote.response.ArticlesItem
import com.dicoding.caloritrack.remote.response.NewsResponse
import com.dicoding.caloritrack.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {
    private val _newsList = MutableLiveData<List<ArticlesItem>>()
    val newsList: LiveData<List<ArticlesItem>> = _newsList

    init {
        fetchNews()
    }

    private fun fetchNews() {
        val service = ApiConfig.getApiService()
        val call = service.getNews("calorie", "health", "en", "a89aed1005d54876abb8a45b5ca5163a")

        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    _newsList.value = response.body()?.articles
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }
}

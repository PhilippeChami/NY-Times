package com.nytimes.android.core.api

import com.nytimes.android.news.models.GlobalResponse
import com.nytimes.android.news.models.NewsModel
import com.nytimes.android.utils.GlobalVars
import retrofit2.http.GET

interface RestService {

      @GET(GlobalVars.API_MOST_POPULAR)
      suspend fun getMostPopularNews(): GlobalResponse<List<NewsModel>>

}
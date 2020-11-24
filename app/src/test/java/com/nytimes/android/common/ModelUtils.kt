package com.nytimes.android.common

import androidx.lifecycle.MutableLiveData
import com.nytimes.android.core.vo.Resource
import com.nytimes.android.news.models.GlobalResponse
import com.nytimes.android.news.models.NewsModel

class ModelUtils {

    companion object{

       fun getLiveDataEmptySuccessList(): MutableLiveData<Resource<GlobalResponse<List<NewsModel>>>>{
           val list:List<NewsModel> = arrayListOf()
           val response = GlobalResponse(list,1,"","")
           val resource  = Resource.success(response)
           return MutableLiveData(resource)
       }

       fun getLiveDataEmptyErrorList(): MutableLiveData<Resource<GlobalResponse<List<NewsModel>>>>{
           val resource  = Resource.error("",null)
           return MutableLiveData(resource)
       }

       fun getEmptySuccessList(): Resource<GlobalResponse<List<NewsModel>>>{
           val list:List<NewsModel> = arrayListOf()
           val response = GlobalResponse(list,1,"","")
           val resource  = Resource.success(response)
           return resource
       }

       fun getEmptyErrorList(): Resource<GlobalResponse<List<NewsModel>>>{
           val resource  = Resource.error("", null)
           return resource
       }

        fun getResponseSuccessList(): GlobalResponse<List<NewsModel>>{
            val list:List<NewsModel> = arrayListOf()
            val response = GlobalResponse(list,1,"","")
            return response
        }

        fun getResponseNotEmptyList(): GlobalResponse<List<NewsModel>>{
            val list:ArrayList<NewsModel> = arrayListOf()
            list.add(NewsModel("","",0,"","",0,null,"","",
                "","","","","","","",""))
            val response = GlobalResponse(list,1,"","") as GlobalResponse<List<NewsModel>>
            return response
        }


    }
}
package com.nytimes.android.news.models


import com.google.gson.annotations.SerializedName

data class GlobalResponse<T>(
    @SerializedName("results")
    var results: T,
    @SerializedName("num_results")
    val numResults: Int?,
    @SerializedName("copyright")
    var copyright: String?,
    @SerializedName("status")
    var status: String?
)
package com.hyeonbin.github_user_search.network.model.response.recommend_user

import com.google.gson.annotations.SerializedName

data class ResponseBaseRecommendUserSearchData(
    @SerializedName(value = "total_count")
    val totalCount: Int?,
    @SerializedName(value = "incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName(value = "items")
    val userResult: List<ResponseRecommendUserSearchData>
)
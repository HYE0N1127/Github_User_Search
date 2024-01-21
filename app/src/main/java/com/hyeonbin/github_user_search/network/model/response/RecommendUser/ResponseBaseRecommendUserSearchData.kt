package com.hyeonbin.github_user_search.network.model.response.RecommendUser

import com.google.gson.annotations.SerializedName

data class ResponseBaseRecommendUserSearchData(
    @SerializedName(value = "total_count")
    val totalCount: Int?,
    @SerializedName(value = "incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName(value = "items")
    val userResult: List<ResponseRecommendUserSearchData>
)
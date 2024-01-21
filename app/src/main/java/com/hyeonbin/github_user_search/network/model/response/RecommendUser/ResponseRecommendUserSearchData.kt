package com.hyeonbin.github_user_search.network.model.response.RecommendUser

import com.google.gson.annotations.SerializedName

data class ResponseRecommendUserSearchData(
    @SerializedName(value = "login")
    val login: String?,
    @SerializedName(value = "id")
    val id: Int?,
    @SerializedName(value = "avatar_url")
    val avatarUrl: String?
)
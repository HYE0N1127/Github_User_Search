package com.hyeonbin.github_user_search.mapper

import com.hyeonbin.github_user_search.entity.RecommendUser
import com.hyeonbin.github_user_search.network.model.response.ResponseRecommendUserSearchData

fun ResponseRecommendUserSearchData.toEntity() = RecommendUser(
    login ?: "null",
    id ?: 0,
    avatarUrl ?: "null"
)
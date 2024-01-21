package com.hyeonbin.github_user_search.mapper

import com.hyeonbin.github_user_search.entity.RecommendUser
import com.hyeonbin.github_user_search.entity.RecommendUserItem
import com.hyeonbin.github_user_search.network.model.response.RecommendUser.ResponseBaseRecommendUserSearchData
import com.hyeonbin.github_user_search.network.model.response.RecommendUser.ResponseRecommendUserSearchData

fun ResponseRecommendUserSearchData.toEntity() = RecommendUserItem(
    login ?: "null",
    id ?: 0,
    avatarUrl ?: "null"
)

fun ResponseBaseRecommendUserSearchData.toEntity() = RecommendUser(
    totalCount ?: 0,
    incompleteResults ?: false,
    userResult.map { it.toEntity() }
)
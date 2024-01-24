package com.hyeonbin.github_user_search.mapper

import com.hyeonbin.github_user_search.entity.recommend_user.RecommendUser
import com.hyeonbin.github_user_search.entity.recommend_user.RecommendUserItem
import com.hyeonbin.github_user_search.network.model.response.recommend_user.ResponseBaseRecommendUserSearchData
import com.hyeonbin.github_user_search.network.model.response.recommend_user.ResponseRecommendUserSearchData

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
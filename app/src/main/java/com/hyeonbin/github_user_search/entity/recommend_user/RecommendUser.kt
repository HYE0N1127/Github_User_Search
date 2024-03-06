package com.hyeonbin.github_user_search.entity.recommend_user

data class RecommendUser(
    val totalCount: Int,
    val incompleteResult: Boolean,
    val userResult: List<RecommendUserItem>
)
package com.hyeonbin.github_user_search.entity.recommend_user

data class RecommendUserItem(
    // 검색 결과에 표시될 이름 ex. HYE0N1127
    val displayUserId: String,
    // Index
    val id: Int,
    // 프로필 이미지
    val profileAvatarUrl: String
)
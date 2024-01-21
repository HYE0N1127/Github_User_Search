package com.hyeonbin.github_user_search.repository

import com.hyeonbin.github_user_search.entity.RecommendUser
import com.hyeonbin.github_user_search.entity.RecommendUserItem
import kotlinx.coroutines.flow.Flow

interface RecommendUserRepository {
    suspend fun getRecommendUser(userName: String): Flow<RecommendUser>
}
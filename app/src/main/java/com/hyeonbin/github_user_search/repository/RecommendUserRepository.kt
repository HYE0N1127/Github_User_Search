package com.hyeonbin.github_user_search.repository

import com.hyeonbin.github_user_search.entity.RecommendUser
import kotlinx.coroutines.flow.Flow

interface RecommendUserRepository {
    suspend fun getRecommendUser(userName: String): Flow<List<RecommendUser>>
}
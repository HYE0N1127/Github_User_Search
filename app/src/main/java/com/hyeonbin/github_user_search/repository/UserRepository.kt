package com.hyeonbin.github_user_search.repository

import com.hyeonbin.github_user_search.entity.recommend_user.RecommendUser
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getRecommendUser(userName: String): Flow<RecommendUser>
}
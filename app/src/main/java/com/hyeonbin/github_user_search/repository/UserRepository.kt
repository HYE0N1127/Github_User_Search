package com.hyeonbin.github_user_search.repository

import com.hyeonbin.github_user_search.entity.recommend_user.RecommendUser
import com.hyeonbin.github_user_search.entity.user.User
import com.hyeonbin.github_user_search.state.UiState
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getRecommendUser(userName: String): Flow<UiState<RecommendUser>>
    suspend fun getUserDetail(userName: String): Flow<User>
}
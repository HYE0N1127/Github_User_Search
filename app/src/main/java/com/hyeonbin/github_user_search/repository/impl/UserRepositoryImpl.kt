package com.hyeonbin.github_user_search.repository.impl

import com.hyeonbin.github_user_search.entity.recommend_user.RecommendUser
import com.hyeonbin.github_user_search.entity.user.User
import com.hyeonbin.github_user_search.mapper.toEntity
import com.hyeonbin.github_user_search.network.client.RetrofitClient
import com.hyeonbin.github_user_search.repository.UserRepository
import com.hyeonbin.github_user_search.state.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl : UserRepository {
    private val userService = RetrofitClient.getUserService()
    override suspend fun getRecommendUser(userName: String): Flow<UiState<RecommendUser>> = flow {
        val response = userService.searchRecommendUser(userName)
        if (response.isSuccessful) {
            when {
                response.body() == null -> {
                    emit(UiState.Error)
                }

                response.body() != null -> {
                    val value = response.body()!!.toEntity()
                    emit(UiState.Success(value))
                }
            }
        } else {
            // TODO(에러 상황에서 빈 리스트가 아닌 특정 에러를 알려줄 만한 방안이 필요함)
            emit(UiState.Error)
        }
    }

    override suspend fun getUserDetail(userName: String): Flow<UiState<User>> = flow {
        val response = userService.getDetailUserInfo(userName)
        if (response.isSuccessful) {
            when {
                response.body() == null -> {
                    emit(UiState.Error)
                }

                response.body() != null -> {
                    val value = response.body()!!.toEntity()
                    emit(UiState.Success(value))
                }
            }
        } else {
            // TODO(에러 상황에서 빈 리스트가 아닌 특정 에러를 알려줄 만한 방안이 필요함)
            emit(UiState.Error)
        }
    }
}
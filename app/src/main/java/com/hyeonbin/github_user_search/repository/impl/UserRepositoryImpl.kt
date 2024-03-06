package com.hyeonbin.github_user_search.repository.impl

import com.hyeonbin.github_user_search.entity.recommend_user.RecommendUser
import com.hyeonbin.github_user_search.entity.user.User
import com.hyeonbin.github_user_search.mapper.toEntity
import com.hyeonbin.github_user_search.network.client.RetrofitClient
import com.hyeonbin.github_user_search.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl : UserRepository {
    private val userService = RetrofitClient.getUserService()
    override suspend fun getRecommendUser(userName: String): Flow<RecommendUser> = flow {
        val response = userService.searchRecommendUser(userName)
        val emptyData = RecommendUser(0, true, emptyList())
        if (response.isSuccessful) {
            when {
                response.body() == null -> {
                    emit(emptyData)
                }

                response.body() != null -> {
                    val value = response.body()!!.toEntity()
                    emit(value)
                }
            }
        } else {
            // TODO(에러 상황에서 빈 리스트가 아닌 특정 에러를 알려줄 만한 방안이 필요함)
            emit(emptyData)
        }
    }

    override suspend fun getUserDetail(userName: String): Flow<User> = flow {
        val response = userService.getDetailUserInfo(userName)
        if (response.isSuccessful) {
            if (response.body() == null) {
                val errorData = User("", 0, "", "", "", false, 0, 0, "", "", "", isError = true, errorMessage = "값이 비어있습니다.")
                emit(errorData)
            } else {
                val value = response.body()!!.toEntity()
                emit(value)
            }
        } else {
            val errorData = User("", 0, "", "", "", false, 0, 0, "", "", "", isError = true, errorMessage = "오류가 발생하였습니다.")
            emit(errorData)
        }
    }
}
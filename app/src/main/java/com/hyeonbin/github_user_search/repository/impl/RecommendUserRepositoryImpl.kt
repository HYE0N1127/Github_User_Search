package com.hyeonbin.github_user_search.repository.impl

import com.hyeonbin.github_user_search.entity.RecommendUser
import com.hyeonbin.github_user_search.mapper.toEntity
import com.hyeonbin.github_user_search.network.client.RetrofitClient
import com.hyeonbin.github_user_search.repository.RecommendUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class RecommendUserRepositoryImpl: RecommendUserRepository {
    private val recommendUserService = RetrofitClient.getRecommendUserService()
    override suspend fun getRecommendUser(userName: String): Flow<List<RecommendUser>> = flow {
        val response = recommendUserService.searchRecommendUser(userName)
        if (response.isSuccessful) {
            when {
                response.body() == null -> {
                    emit(emptyList())
                }
                response.body() != null -> {
                    if (response.body()!!.isEmpty()) {
                        emit(emptyList())
                    } else {
                        val value = response.body()!!.map { it.toEntity() }
                        emit(value)
                    }
                }
            }
        } else {
            // TODO(에러 상황에서 빈 리스트가 아닌 특정 에러를 알려줄 만한 방안이 필요함)
            emit(emptyList())
        }
    }
}
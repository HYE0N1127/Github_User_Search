package com.hyeonbin.github_user_search.repository.impl

import com.hyeonbin.github_user_search.entity.RecommendUser
import com.hyeonbin.github_user_search.entity.RecommendUserItem
import com.hyeonbin.github_user_search.mapper.toEntity
import com.hyeonbin.github_user_search.network.client.RetrofitClient
import com.hyeonbin.github_user_search.repository.RecommendUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecommendUserRepositoryImpl : RecommendUserRepository {
    private val recommendUserService = RetrofitClient.getRecommendUserService()
    override suspend fun getRecommendUser(userName: String): Flow<RecommendUser> = flow {
        val response = recommendUserService.searchRecommendUser(userName)
        val emptyData = RecommendUser(0, true, emptyList())
        if (response.isSuccessful) {
            when {
                response.body() == null -> {
                    emit(emptyData)
                }

                response.body() != null -> {
                    val value = response.body()!!
                    emit(value.toEntity())
                }
            }
        } else {
            // TODO(에러 상황에서 빈 리스트가 아닌 특정 에러를 알려줄 만한 방안이 필요함)
            emit(emptyData)
        }
    }
}
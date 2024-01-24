package com.hyeonbin.github_user_search.network.service

import com.hyeonbin.github_user_search.network.model.response.recommend_user.ResponseBaseRecommendUserSearchData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecommendUserService {

    @GET("search/users")
    suspend fun searchRecommendUser(
        @Query("q") userName: String
    ): Response<ResponseBaseRecommendUserSearchData>
}
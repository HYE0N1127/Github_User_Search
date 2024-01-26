package com.hyeonbin.github_user_search.network.service

import com.hyeonbin.github_user_search.network.model.response.recommend_user.ResponseBaseRecommendUserSearchData
import com.hyeonbin.github_user_search.network.model.response.user.ResponseUserData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("search/users")
    suspend fun searchRecommendUser(
        @Query("q") userName: String
    ): Response<ResponseBaseRecommendUserSearchData>

    @GET("users/{userName}")
    suspend fun getDetailUserInfo(
        @Path("userName") userName: String
    ): Response<ResponseUserData>
}
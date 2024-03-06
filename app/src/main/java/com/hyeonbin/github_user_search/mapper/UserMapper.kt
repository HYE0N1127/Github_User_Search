package com.hyeonbin.github_user_search.mapper

import com.hyeonbin.github_user_search.entity.user.User
import com.hyeonbin.github_user_search.network.model.response.user.ResponseUserData

fun ResponseUserData.toEntity() = User(
    login ?: "",
    id ?: 0,
    profileAvatarUrl ?: "",
    name ?: "",
    type ?: "",
    isSiteAdmin ?: false,
    followers ?: 0,
    following ?: 0,
    location ?: "",
    company ?: "",
    bio ?: ""
)
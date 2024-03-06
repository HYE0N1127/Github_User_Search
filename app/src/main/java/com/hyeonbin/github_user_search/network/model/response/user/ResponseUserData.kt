package com.hyeonbin.github_user_search.network.model.response.user

import com.google.gson.annotations.SerializedName

data class ResponseUserData(
    @SerializedName(value = "login")
    val login: String?,
    @SerializedName(value = "id")
    val id: Int?,
    @SerializedName(value = "avatar_url")
    val profileAvatarUrl: String?,
    @SerializedName(value = "name")
    val name: String?,
    @SerializedName(value = "type")
    val type: String?,
    @SerializedName(value = "site_admin")
    val isSiteAdmin: Boolean?,
    @SerializedName(value = "followers")
    val followers: Int?,
    @SerializedName(value = "following")
    val following: Int?,
    @SerializedName(value = "location")
    val location: String?,
    @SerializedName(value = "company")
    val company: String?,
    @SerializedName(value = "bio")
    val bio: String?
)
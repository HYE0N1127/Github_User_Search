package com.hyeonbin.github_user_search.entity.user

data class User (
    // 아이디 (ex. HYE0N1127)
    val login: String,
    // 인덱스
    val id: Int,
    // 프로필 아바타
    val profileAvatarUrl: String,
    // 이름 (ex. 황현빈)
    val name: String,
    // 유저인지에 대한 타입
    val type: String,
    // 깃허브의 어드민인지
    val isSiteAdmin: Boolean,
    // 팔로워 수
    val follower: Int,
    // 팔로잉 수
    val following: Int,
    // 현재 거주 중인 위치
    val location: String,
    // 현재 재직 중인 회사
    val company: String,
    // 소셜 네트워크 URL(ex. Blog)
    val blog: String,
    // 한 줄 소개
    val bio: String
)
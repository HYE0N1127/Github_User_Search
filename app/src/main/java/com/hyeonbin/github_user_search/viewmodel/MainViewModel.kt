package com.hyeonbin.github_user_search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyeonbin.github_user_search.entity.recommend_user.RecommendUser
import com.hyeonbin.github_user_search.repository.UserRepository
import com.hyeonbin.github_user_search.repository.impl.UserRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val repository: UserRepository by lazy { UserRepositoryImpl() }

    // Server Networking Variable
    private val _recommendUserFlow: MutableStateFlow<RecommendUser> = MutableStateFlow(RecommendUser(0, true, emptyList()))
    val recommendUserFlow: StateFlow<RecommendUser> = _recommendUserFlow.asStateFlow()

    fun searchUserList(searchQuery: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRecommendUser(searchQuery).collect {
                _recommendUserFlow.emit(it)
            }
        }
    }
}
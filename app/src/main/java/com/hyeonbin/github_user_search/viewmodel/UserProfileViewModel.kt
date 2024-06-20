package com.hyeonbin.github_user_search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyeonbin.github_user_search.entity.recommend_user.RecommendUser
import com.hyeonbin.github_user_search.entity.user.User
import com.hyeonbin.github_user_search.repository.UserRepository
import com.hyeonbin.github_user_search.repository.impl.UserRepositoryImpl
import com.hyeonbin.github_user_search.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UserProfileViewModel: ViewModel() {
    private val repository: UserRepository by lazy { UserRepositoryImpl() }

    private val _userDetailFlow = MutableStateFlow<UiState<User>>(UiState.Loading)
    val userDetailFlow: StateFlow<UiState<User>> = _userDetailFlow.asStateFlow()

    fun getUserDetail(userName: String) = viewModelScope.launch(Dispatchers.IO) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUserDetail(userName).catch {
                _userDetailFlow.emit(UiState.Error)
            }.collect {
                _userDetailFlow.emit(it)
            }
        }
    }
}
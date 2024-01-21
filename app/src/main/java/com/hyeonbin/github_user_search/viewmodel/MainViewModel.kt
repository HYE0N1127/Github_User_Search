package com.hyeonbin.github_user_search.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyeonbin.github_user_search.entity.RecommendUser
import com.hyeonbin.github_user_search.entity.RecommendUserItem
import com.hyeonbin.github_user_search.repository.RecommendUserRepository
import com.hyeonbin.github_user_search.repository.impl.RecommendUserRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val repository: RecommendUserRepository by lazy { RecommendUserRepositoryImpl() }

    // Data Binding Variable
    private val _searchUserQuery: MutableStateFlow<String> = MutableStateFlow("")
    val searchUserQuery: StateFlow<String> = _searchUserQuery.asStateFlow()

    // Server Networking Variable
    private val _recommendUserFlow: MutableStateFlow<RecommendUser> = MutableStateFlow(RecommendUser(0, true, emptyList()))
    val recommendUserFlow: StateFlow<RecommendUser> = _recommendUserFlow.asStateFlow()

    fun searchUserList(searchQuery: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRecommendUser(searchQuery).collect {
                Log.d("Test5", "$it")
//                _recommendUserFlow.emit(it)
            }
        }
    }
}
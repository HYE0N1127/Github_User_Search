package com.hyeonbin.github_user_search.state

sealed class UiState<out T>(val _data: T?) {
    data object Loading : UiState<Nothing>(_data = null)
    data object Error : UiState<Nothing>(_data = null)
    data class Success<out R>(val data: R) : UiState<R>(_data = data)
}
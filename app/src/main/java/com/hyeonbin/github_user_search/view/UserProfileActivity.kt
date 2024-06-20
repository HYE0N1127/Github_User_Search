package com.hyeonbin.github_user_search.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hyeonbin.github_user_search.R
import com.hyeonbin.github_user_search.databinding.ActivityUserProfileBinding
import com.hyeonbin.github_user_search.state.UiState
import com.hyeonbin.github_user_search.util.Constants
import com.hyeonbin.github_user_search.viewmodel.UserProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UserProfileActivity : AppCompatActivity() {
    private lateinit var userName: String
    private val binding: ActivityUserProfileBinding by lazy { ActivityUserProfileBinding.inflate(layoutInflater) }
    private val viewModel: UserProfileViewModel by lazy { UserProfileViewModel() }
    private var isFirstLoadState = true

    private var isFirstExecution = true // 최초 실행 여부를 나타내는 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        userName = intent.getStringExtra(Constants.USER_NAME).toString()
        checkInvalidUserName(userName)
        initCollector()
    }

    private fun checkInvalidUserName(userName: String) {
        if (userName.isEmpty()) {
            Toast.makeText(this, "유저 이름을 다시 확인해 주세요.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun initCollector() = lifecycleScope.launch(Dispatchers.Main) {
        with(viewModel) {
            getUserDetail(userName)
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                userDetailFlow.collectLatest { recommendUserState ->
                    when (recommendUserState) {
                        is UiState.Loading -> {
                        }
                        is UiState.Success -> {
                            Toast.makeText(this@UserProfileActivity, "서버 통신 성공!", Toast.LENGTH_SHORT).show()
                        }
                        is UiState.Error -> {
                        }
                    }
                }

            }
        }
    }
}
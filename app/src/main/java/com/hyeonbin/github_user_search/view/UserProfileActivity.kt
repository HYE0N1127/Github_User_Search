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
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                if (!isFirstLoadState) {
                    isFirstLoadState = true
                    viewModel.getUserDetail(userName)
                } else {
                    viewModel.getUserDetail(userName)
                }

                userDetailFlow.collectLatest {
                    if (it.login.isEmpty() && !isFirstExecution) {
                        Toast.makeText(this@UserProfileActivity, "에러가 발생하였습니다. ${it.errorMessage}", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@UserProfileActivity, "서버 통신에 성공하였습니다", Toast.LENGTH_SHORT).show()
                        Log.d("Hyeon", "$it")
                    }
                    // 최초 실행 플래그
                    if (isFirstExecution) {
                        isFirstExecution = false
                    }
                }
            }
        }
    }
}
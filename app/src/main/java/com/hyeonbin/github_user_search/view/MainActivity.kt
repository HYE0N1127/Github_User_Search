package com.hyeonbin.github_user_search.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hyeonbin.github_user_search.adapter.RecommendUserAdapter
import com.hyeonbin.github_user_search.databinding.ActivityMainBinding
import com.hyeonbin.github_user_search.state.UiState
import com.hyeonbin.github_user_search.util.Constants
import com.hyeonbin.github_user_search.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()
    private val adapter: RecommendUserAdapter by lazy { RecommendUserAdapter() }

    private var isFirstExecution = true // 최초 실행 여부를 나타내는 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
        initListener()
        initCollector()
    }

    private fun initListener() {
        binding.etSearchUser.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // Editable -> String 형 병환
                val searchQuery = binding.etSearchUser.text.toString()
                viewModel.searchUserList(searchQuery)
                true
            } else {
                false
            }
        }
        adapter.setOnUserItemClickListener {
            val intent = Intent(this, UserProfileActivity::class.java).putExtra(Constants.USER_NAME, it)
            startActivity(intent)
        }
    }

    private fun initUI() {
        binding.rvRecommendUserList.adapter = adapter
        binding.rvRecommendUserList.itemAnimator = null
    }

    private fun initCollector() = lifecycleScope.launch(Dispatchers.Main) {
        with(viewModel) {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                recommendUserFlow.collectLatest { recommendUserState ->
                    when (recommendUserState) {
                        is UiState.Loading -> {
                            setLoadingIndicatorVisible(true)
                        }
                        is UiState.Success -> {
                            setLoadingIndicatorVisible(false)
                            if (recommendUserState.data.userResult.isEmpty()) {
                                Toast.makeText(this@MainActivity, "존재하지 않는 유저입니다.", Toast.LENGTH_SHORT).show()
                            } else {
                                adapter.submitList(recommendUserState.data.userResult)
                            }
                        }
                        is UiState.Error -> {
                            Toast.makeText(this@MainActivity, "에러가 발생하였습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            }
        }
    }

    private fun setLoadingIndicatorVisible(isLoading: Boolean) {
        if (isLoading) {
            binding.pbLoading.visibility = View.VISIBLE
            binding.rvRecommendUserList.visibility = View.GONE
        } else {
            binding.pbLoading.visibility = View.GONE
            binding.rvRecommendUserList.visibility = View.VISIBLE
        }
    }
}
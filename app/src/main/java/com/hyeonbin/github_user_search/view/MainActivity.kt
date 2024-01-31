package com.hyeonbin.github_user_search.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hyeonbin.github_user_search.adapter.RecommendUserAdapter
import com.hyeonbin.github_user_search.databinding.ActivityMainBinding
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
                recommendUserFlow.collectLatest {
                    if (it.userResult.isEmpty() && !isFirstExecution) {
                        Toast.makeText(this@MainActivity, "검색 결과가 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        adapter.submitList(it.userResult)
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
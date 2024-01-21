package com.hyeonbin.github_user_search.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.hyeonbin.github_user_search.databinding.ActivityMainBinding
import com.hyeonbin.github_user_search.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initListener()
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
    }

    private suspend fun initCollector() = lifecycleScope.launch(Dispatchers.Main) {
        with(viewModel) {
            recommendUserFlow.collectLatest {
                // TODO(어댑터 연결 로직 추가)
            }
        }
    }
}
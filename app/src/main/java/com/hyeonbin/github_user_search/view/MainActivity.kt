package com.hyeonbin.github_user_search.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hyeonbin.github_user_search.R
import com.hyeonbin.github_user_search.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
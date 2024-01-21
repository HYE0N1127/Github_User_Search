package com.hyeonbin.github_user_search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hyeonbin.github_user_search.adapter.difftuil.RecommendUserDiffUtil
import com.hyeonbin.github_user_search.databinding.ItemRecommendUserBinding
import com.hyeonbin.github_user_search.entity.RecommendUser
import com.hyeonbin.github_user_search.entity.RecommendUserItem

class RecommendUserAdapter : ListAdapter<RecommendUserItem, RecommendUserAdapter.RecommendUserViewHolder>(RecommendUserDiffUtil) {
    inner class RecommendUserViewHolder(private val binding: ItemRecommendUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecommendUserItem) {
            // Listener
            binding.root.setOnClickListener {
                userItemClickListener?.invoke(item.displayUserId)
            }

            // UI Impl
            binding.tvUserName.text = item.displayUserId
            Glide.with(binding.root)
                .load(item.profileAvatarUrl)
                .into(binding.ivUserProfileImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendUserViewHolder {
        return RecommendUserViewHolder(
            ItemRecommendUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecommendUserViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    private var userItemClickListener: ((String) -> Unit)? = null

    fun setOnUserItemClickListener(callback: (String) -> Unit) {
        userItemClickListener = callback
    }
}
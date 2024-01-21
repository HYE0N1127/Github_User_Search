package com.hyeonbin.github_user_search.adapter.difftuil

import androidx.recyclerview.widget.DiffUtil
import com.hyeonbin.github_user_search.entity.RecommendUserItem

object RecommendUserDiffUtil: DiffUtil.ItemCallback<RecommendUserItem>() {
    override fun areItemsTheSame(oldItem: RecommendUserItem, newItem: RecommendUserItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: RecommendUserItem,
        newItem: RecommendUserItem
    ): Boolean {
        return oldItem.id == newItem.id
    }
}
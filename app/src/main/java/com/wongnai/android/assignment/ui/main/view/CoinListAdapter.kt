package com.wongnai.android.assignment.ui.main.view

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

class CoinListAdapter(private val onCoinClickListener: CoinListViewHolder.OnCoinClickListener) :
    ListAdapter<CoinListViewHolder.UiModel, CoinListViewHolder>(
        CoinListViewHolder.UiModelDiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListViewHolder {
        return CoinListViewHolder.create(parent, onCoinClickListener)
    }

    override fun onBindViewHolder(holder: CoinListViewHolder, position: Int) {
        holder.fillData(getItem(position))
    }
}

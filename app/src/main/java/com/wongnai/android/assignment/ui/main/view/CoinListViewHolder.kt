package com.wongnai.android.assignment.ui.main.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wongnai.android.assignment.R
import com.wongnai.android.assignment.utils.inflate
import com.wongnai.android.assignment.utils.load
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_item_coin.*

class CoinListViewHolder(
    override val containerView: View,
    private val onCoinClickListener: OnCoinClickListener
) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    companion object {
        fun create(
            parent: ViewGroup,
            onCoinClickListener: OnCoinClickListener
        ): CoinListViewHolder {
            return CoinListViewHolder(
                parent.inflate(R.layout.view_item_coin, false),
                onCoinClickListener
            )
        }
    }

    init {
        itemView.setOnClickListener {
            mData?.id?.let {
                onCoinClickListener.onCoinClick(it)
            }
        }
    }

    private var mData: UiModel? = null

    fun fillData(data: UiModel) {
        this.mData = data
        iconImageView.load(data.iconUrl)
        nameTextView.text = data.name
        descriptionTextView.text = data.description
        priceTextView.text = data.price
    }

    interface OnCoinClickListener {
        fun onCoinClick(id: Long)
    }

    data class UiModel(
        val id: Long,
        val iconUrl: String,
        val name: String,
        val description: String,
        val price: String
    )

    class UiModelDiffCallback : DiffUtil.ItemCallback<UiModel>() {
        override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
            return oldItem == newItem
        }
    }
}

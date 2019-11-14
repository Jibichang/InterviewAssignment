package com.wongnai.android.assignment.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.wongnai.android.assignment.R
import kotlinx.android.synthetic.main.view_coin_stat.view.*

class CoinStatisticView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_coin_stat, this)
    }

    fun fillData(data: UiModel) {
        statNameTextView.text = data.statName
        statValueTextView.text = data.statValue
    }

    data class UiModel(
        val statName: String,
        val statValue: String
    )
}

package com.wongnai.android.assignment.ui.info

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.wongnai.android.assignment.utils.load
import kotlinx.android.synthetic.main.fragment_coin_info.*
import kotlinx.android.synthetic.main.layout_coin_price.*
import kotlin.random.Random
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.wongnai.android.assignment.R
import com.wongnai.android.assignment.utils.dpToPx
import com.wongnai.android.assignment.view.CoinStatisticView
import kotlinx.android.synthetic.main.layout_coin_stat.*


class CoinInfoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coin_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iconImageView.load("https://via.placeholder.com/150")

        priceChart.setBackgroundColor(Color.TRANSPARENT)

        priceChart.apply {
            description.isEnabled = false
            setPinchZoom(false)
            isDragEnabled = false
            setScaleEnabled(false)

            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                setDrawAxisLine(false)
                setDrawGridLines(false)
                setDrawLabels(false)
            }

            axisLeft.apply {
                setDrawLabels(false)
                setDrawAxisLine(false)
                setDrawGridLines(false)
                setDrawZeroLine(false)
            }
            axisRight.isEnabled = false
            legend.apply {
                isEnabled = false
            }
        }

        val prices = mutableListOf<Entry>()
        repeat(10) {
            prices.add(Entry(it.toFloat(), Random.nextFloat()))
        }
        val dataSet = LineDataSet(prices, "dataSet1").apply {
            setDrawCircles(false)
            valueTextColor = ContextCompat.getColor(requireContext(), R.color.light_gray)
        }
        dataSet.values = prices
        val dataSets = listOf<ILineDataSet>(dataSet)
        val lineData = LineData(dataSets)
        priceChart.data = lineData

        repeat(5) {
            val v = CoinStatisticView(requireContext())
            v.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT).apply {
                topMargin = requireContext().dpToPx(R.dimen.space_normal)
            }
            v.fillData(CoinStatisticView.UiModel("Market Cap", "13298469"))
            coinStatsLayout.addView(v)
        }
    }
}

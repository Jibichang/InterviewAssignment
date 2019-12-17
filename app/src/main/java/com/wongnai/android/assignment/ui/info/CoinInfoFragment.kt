package com.wongnai.android.assignment.ui.info

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.wongnai.android.assignment.R
import com.wongnai.android.assignment.api.CoinApi
import com.wongnai.android.assignment.model.CoinInfo
import com.wongnai.android.assignment.model.CoinResponse
import com.wongnai.android.assignment.utils.load
import kotlinx.android.synthetic.main.fragment_coin_info.*
import kotlinx.android.synthetic.main.layout_coin_price.*
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Response
import kotlin.random.Random


class CoinInfoFragment : Fragment() {
    private val coinApi: CoinApi by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = CoinInfoFragmentArgs.fromBundle(arguments!!)
        Toast.makeText(context,"${args.coinId}", Toast.LENGTH_SHORT).show()
        serviceCoin(args.coinId)
        return inflater.inflate(R.layout.fragment_coin_info, container, false)
    }

    private fun serviceCoin(coinId : Long){
        coinApi.getCoin(coinId).enqueue(object : retrofit2.Callback<CoinResponse> {
            override fun onFailure(call: Call<CoinResponse>, t: Throwable) {
                Toast.makeText(context, "NO", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<CoinResponse>, response: Response<CoinResponse>) {
                if (response.isSuccessful){
                    val result = response.body()!!.data.coin
                    setCoinInfo(result)

                    Toast.makeText(context, "${result.name}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpChart()
        fillChart()

    }

    private fun setCoinInfo(coin: CoinInfo){
        nameTextView.text = coin.name
        descriptionTextView.text = coin.description
        iconImageView.load(coin.iconUrl)
    }

    private fun getSocialIcon(type: String?): Int {
        return when (type) {
            "twitter" -> R.drawable.ic64_twitter
            "telegram" -> R.drawable.ic64_telegram
            "reddit" -> R.drawable.ic64_reddit
            "discord" -> R.drawable.ic64_discord
            "medium" -> R.drawable.ic64_medium
            "bitcointalk" -> R.drawable.ic64_bitcointalk
            "youtube" -> R.drawable.ic64_youtube
            "facebook" -> R.drawable.ic64_facebook
            "instagram" -> R.drawable.ic64_instagram
            "github" -> R.drawable.ic64_github
            else -> R.drawable.ic64_web
        }
    }

    private fun fillChart() {
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
        priceChart.notifyDataSetChanged()
        priceChart.invalidate()
    }

    private fun setUpChart() {
        priceChart.apply {
            setBackgroundColor(Color.TRANSPARENT)
            description.isEnabled = false
            setPinchZoom(false)
            isDragEnabled = false
            setScaleEnabled(false)
            setTouchEnabled(false)

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
    }
}

package com.wongnai.android.assignment.ui.info

import android.annotation.SuppressLint
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
import com.wongnai.android.assignment.model.DataInfo
import com.wongnai.android.assignment.utils.load
import com.wongnai.android.assignment.view.CoinStatisticView
import kotlinx.android.synthetic.main.fragment_coin_info.*
import kotlinx.android.synthetic.main.layout_all_time_high.*
import kotlinx.android.synthetic.main.layout_coin_price.*
import kotlinx.android.synthetic.main.layout_coin_stat.*
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random


class CoinInfoFragment : Fragment() {
    private val coinApi: CoinApi by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_coin_info, container, false)
    }

    private fun serviceCoin(coinId : Long){
        coinApi.getCoin(coinId).enqueue(object : retrofit2.Callback<CoinResponse> {
            override fun onFailure(call: Call<CoinResponse>, t: Throwable) {
                Toast.makeText(context, "NO", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<CoinResponse>, response: Response<CoinResponse>) {
                if (response.isSuccessful){
                    val result = response.body()!!.data
                    setupCoinInfo(result)
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpChart()
        fillChart()
        val args = CoinInfoFragmentArgs.fromBundle(arguments!!)
        serviceCoin(args.coinId)
    }

    private fun setupCoinInfo(info: DataInfo){
        val name = java.lang.String.format(getString(R.string.coin_name), info.coin.name, info.coin.symbol)
        val price = java.lang.String.format(getString(R.string.coin_price), info.coin.price!!.toFloat(), info.base.symbol)
        val change = kotlin.math.abs(info.coin.change)
        val volume = java.lang.String.format(getString(R.string.coin_volume), info.coin.volume)

        nameTextView.text = name
        descriptionTextView.text = info.coin.description
        iconImageView.load(info.coin.iconUrl)

        //coin price
        priceTextView.text = price
        if (info.coin.change < 0) diffTextView.setTextColor(Color.RED) else diffTextView.setTextColor(Color.GREEN)
        diffTextView.text = java.lang.String.format(getString(R.string.coin_change), change)
        volumeTextView.text = volume

        //all time high
        allTimeHeightTextView.text = info.coin.allTimeHigh.price
        allTimeHeightTimestampTextView.text = getDateTime(info.coin.allTimeHigh.timestamp)

        //social
//        val txt2 = TextView(context)
//        txt2.text = "Hi!"

        //statistic
        setStatisticList(info.coin)
    }

    private fun setStatisticList(coin: CoinInfo){
        val firstSeen = CoinStatisticView(context!!,null,0)
        firstSeen.fillData(CoinStatisticView.UiModel(statName = getString(R.string.stat_first_seen), statValue = coin.firstSeen!!))
        coinStatsLayout.addView(firstSeen)

        val marketCap = CoinStatisticView(context!!,null,0)
        marketCap.fillData(CoinStatisticView.UiModel(statName = getString(R.string.stat_market_cap), statValue = coin.marketCap!!))
        coinStatsLayout.addView(marketCap)

        val numberOfExchanges = CoinStatisticView(context!!,null,0)
        numberOfExchanges.fillData(CoinStatisticView.UiModel(statName = getString(R.string.stat_number_of_exchange), statValue = coin.numberOfExchanges!!))
        coinStatsLayout.addView(numberOfExchanges)

        val numberOfMarkets = CoinStatisticView(context!!,null,0)
        numberOfMarkets.fillData(CoinStatisticView.UiModel(statName = getString(R.string.stat_number_of_market), statValue = coin.numberOfMarkets!!))
        coinStatsLayout.addView(numberOfMarkets)
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

    private fun getDateTime(str: String?): String? {
        val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss",
            Locale.ENGLISH)
        return  sdf.format(Date(str!!.toLong()))
    }

}

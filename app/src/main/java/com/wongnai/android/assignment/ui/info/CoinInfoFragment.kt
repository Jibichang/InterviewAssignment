package com.wongnai.android.assignment.ui.info

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.android.flexbox.FlexboxLayout
import com.wongnai.android.assignment.R
import com.wongnai.android.assignment.api.CoinApi
import com.wongnai.android.assignment.model.AllTimeHigh
import com.wongnai.android.assignment.model.Coin
import com.wongnai.android.assignment.model.CoinResponse
import com.wongnai.android.assignment.model.Social
import com.wongnai.android.assignment.utils.dpToPx
import com.wongnai.android.assignment.utils.load
import com.wongnai.android.assignment.utils.toast
import com.wongnai.android.assignment.view.CoinStatisticView
import kotlinx.android.synthetic.main.fragment_coin_info.*
import kotlinx.android.synthetic.main.layout_all_time_high.*
import kotlinx.android.synthetic.main.layout_coin_price.*
import kotlinx.android.synthetic.main.layout_coin_stat.*
import kotlinx.android.synthetic.main.layout_social_media.*
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*


class CoinInfoFragment : Fragment() {
    companion object {
        private const val EXTRA_COIN_ID = "extra-coin-id"

        fun newInstance(id: Long): CoinInfoFragment {
            return CoinInfoFragment().apply {
                arguments = bundleOf(EXTRA_COIN_ID to id)
            }
        }
    }

    private val mDateTimeFormat = SimpleDateFormat("YYYY-MM-dd HH:mm:ss", Locale.US)
    private val mDateFormat = SimpleDateFormat("YYYY-MMM-dd", Locale.US)
    private var mCoinId: Long = 0L
    private val coinApi: CoinApi by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCoinId = arguments?.getLong(EXTRA_COIN_ID) ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coin_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpChart()

        coinApi.getCoin(mCoinId).enqueue(object : Callback<CoinResponse> {
            override fun onFailure(call: Call<CoinResponse>, t: Throwable) {
                Timber.e(t)
                context?.toast(t.message ?: "Unknown Error")
            }

            override fun onResponse(call: Call<CoinResponse>, response: Response<CoinResponse>) {
                if (response.isSuccessful) {
                    response.body()?.data?.coin?.let { coin ->
                        val base = response.body()?.data?.base?.symbol ?: ""
                        iconImageView.load(coin.iconUrl)
                        nameTextView.text =
                            context?.getString(R.string.coin_name, coin.name, coin.symbol)
                        descriptionTextView.text = coin.description
                        priceTextView.text =
                            context?.getString(R.string.coin_price, coin.price, base)
                        fillDiff(coin.change)
                        volumeTextView.text = context?.getString(R.string.coin_volume, coin.volume)
                        fillChart(coin.history)
                        fillAllTimeHeight(coin.allTimeHigh)
                        websiteTextView.text = coin.websiteUrl
                        fillSocial(coin.socials)
                        fillStat(coin)
                    }
                } else {
                    val message = response.errorBody()?.string() ?: "Unknown error"
                    Timber.e(message)
                    context?.toast(message)
                }
            }
        })
    }

    private fun fillDiff(diff: Double) {
        if (diff > 0) {
            context?.let {c ->
                diffTextView.setTextColor(ContextCompat.getColor(c, R.color.green))
            }
        } else {
            context?.let {c ->
                diffTextView.setTextColor(ContextCompat.getColor(c, R.color.red))
            }
        }
        diffTextView.text = context?.getString(R.string.coin_change, diff)
    }

    private fun fillStat(coin: Coin) {
        fillFirstSeen(coin)
        fillMarketCap(coin)
        fillNumberOfMarket(coin)
        fillNumberOfExchange(coin)
    }

    private fun fillNumberOfExchange(coin: Coin) {
        val numberOfExchange = createCoinStatView()
        numberOfExchange.fillData(
            CoinStatisticView.UiModel(
                context?.getString(R.string.stat_number_of_exchange) ?: "",
                coin.numberOfExchanges.toString()
            )
        )
        coinStatsLayout.addView(numberOfExchange)
    }

    private fun fillNumberOfMarket(coin: Coin) {
        val numberOfMarket = createCoinStatView()
        numberOfMarket.fillData(
            CoinStatisticView.UiModel(
                context?.getString(R.string.stat_number_of_market) ?: "",
                coin.numberOfMarkets.toString()
            )
        )
        coinStatsLayout.addView(numberOfMarket)
    }

    private fun fillMarketCap(coin: Coin) {
        val marketCapView = createCoinStatView()
        marketCapView.fillData(
            CoinStatisticView.UiModel(
                context?.getString(R.string.stat_market_cap) ?: "", coin.marketCap.toString()
            )
        )
        coinStatsLayout.addView(marketCapView)
    }

    private fun fillFirstSeen(coin: Coin) {
        val firstSeenView = createCoinStatView()
        firstSeenView.fillData(
            CoinStatisticView.UiModel(
                context?.getString(R.string.stat_first_seen) ?: "",
                mDateFormat.format(coin.firstSeen)
            )
        )
        coinStatsLayout.addView(firstSeenView)
    }

    private fun createCoinStatView(): CoinStatisticView {
        val v = CoinStatisticView(requireContext())
        v.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        ).apply {
            topMargin = requireContext().dpToPx(R.dimen.space_normal)
        }
        return v
    }

    private fun fillSocial(socials: List<Social>) {
        socials.forEach {
            val v = AppCompatTextView(context).apply {
                layoutParams = FlexboxLayout.LayoutParams(
                    context.dpToPx(96F),
                    FlexboxLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    marginStart = context.dpToPx(R.dimen.space_normal)
                    marginEnd = context.dpToPx(R.dimen.space_normal)
                }
                setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0,
                    getSocialIcon(it.type),
                    0,
                    0
                )
                compoundDrawablePadding = context.dpToPx(R.dimen.space_normal)
                gravity = Gravity.CENTER
                text = it.name
            }
            socialLayout.addView(v)
        }
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

    private fun fillAllTimeHeight(allTimeHigh: AllTimeHigh?) {
        allTimeHeightTextView.text = allTimeHigh?.price?.toString()
        allTimeHeightTimestampTextView.text = mDateTimeFormat.format(allTimeHigh?.timestamp ?: 0)
    }

    private fun fillChart(history: List<Double>) {
        val prices = mutableListOf<Entry>()
        history.forEachIndexed { index, d ->
            prices.add(Entry(index.toFloat(), d.toFloat()))
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

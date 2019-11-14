package com.wongnai.android.assignment.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.wongnai.android.assignment.R
import com.wongnai.android.assignment.api.CoinApi
import com.wongnai.android.assignment.model.CoinsResponse
import com.wongnai.android.assignment.ui.main.view.CoinListAdapter
import com.wongnai.android.assignment.ui.main.view.CoinListViewHolder
import com.wongnai.android.assignment.utils.toast
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainFragment : Fragment(), CoinListViewHolder.OnCoinClickListener {
    private val coinApi: CoinApi by inject()
    private val mAdapter = CoinListAdapter(this)
    private var mCallback: OnCoinClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coinsRecyclerView.layoutManager = LinearLayoutManager(context)
        coinsRecyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        coinsRecyclerView.adapter = mAdapter

        coinApi.getCoins().enqueue(object : Callback<CoinsResponse> {
            override fun onFailure(call: Call<CoinsResponse>, t: Throwable) {
                Timber.e(t)
                context?.toast(t.message ?: "Unknown Error")
                mAdapter.submitList(listOf())
            }

            override fun onResponse(call: Call<CoinsResponse>, response: Response<CoinsResponse>) {
                if (response.isSuccessful) {
                    response.body()?.data?.let { data ->
                        val base = data.stats.base
                        val coins = data.coins.map {
                            CoinListViewHolder.UiModel(
                                id = it.id,
                                iconUrl = it.iconUrl ?: "",
                                name = it.name ?: "",
                                description = it.description ?: "",
                                price = "${it.price} $base"
                            )
                        }
                        mAdapter.submitList(coins)
                    }
                } else {
                    val message = response.errorBody()?.string() ?: "Unknown error"
                    Timber.e(message)
                    context?.toast(message)
                }
            }
        })
    }

    override fun onCoinClick(id: Long) {
        mCallback?.onCoinClick(id)
    }

    fun setOnCoinClickListener(onCoinClickListener: OnCoinClickListener) {
        this.mCallback = onCoinClickListener
    }

    interface OnCoinClickListener {
        fun onCoinClick(id: Long)
    }
}

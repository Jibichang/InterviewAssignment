package com.wongnai.android.assignment.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.wongnai.android.assignment.R
import com.wongnai.android.assignment.api.CoinApi
import com.wongnai.android.assignment.model.CoinsResponse
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Response

class MainFragment : Fragment() {
    private val coinApi: CoinApi by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        binding = DataBindingUtil.inflate(
//            inflater, R.layout.fragment_main, container, false)

        serviceCoins()
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    private fun serviceCoins(){
        coinApi.getCoins().enqueue(object : retrofit2.Callback<CoinsResponse> {
            override fun onFailure(call: Call<CoinsResponse>, t: Throwable) {
                Toast.makeText(context, "NO", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<CoinsResponse>, response: Response<CoinsResponse>) {
                if (response.isSuccessful){
                    val result = response.body()!!.data
                    val adapter = CoinsAdapter(result.coins)
                    coinsRecyclerView.adapter = adapter
                }
            }
        })

    }
}

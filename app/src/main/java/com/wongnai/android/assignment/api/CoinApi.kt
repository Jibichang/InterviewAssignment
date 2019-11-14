package com.wongnai.android.assignment.api

import com.wongnai.android.assignment.model.CoinsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApi {
    @GET("coins")
    fun getCoins(): Call<CoinsResponse>

    @GET("coins/{coinId}")
    fun getCoin(@Path("coinId") coinId: Long): Call<CoinsResponse>
}

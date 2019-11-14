package com.wongnai.android.assignment.api

import com.wongnai.android.assignment.model.CoinsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApi {
    @GET("coins")
    suspend fun getCoins(): Response<CoinsResponse>

    @GET("coins/{coinId}")
    suspend fun getCoin(@Path("coinId") coinId: Long): Response<CoinsResponse>
}

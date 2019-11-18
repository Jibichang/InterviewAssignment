package com.wongnai.android.assignment.model

import com.google.gson.annotations.SerializedName

data class CoinData(
    @field:SerializedName("base")
    val base: Base? = null,
    @field:SerializedName("coin")
    val coin: Coin? = null
)

package com.wongnai.android.assignment.model

import com.google.gson.annotations.SerializedName

data class CoinData(
    @field:SerializedName("coins")
    val coins: Coin? = null
)

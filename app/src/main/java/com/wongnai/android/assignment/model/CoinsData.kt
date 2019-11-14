package com.wongnai.android.assignment.model

import com.google.gson.annotations.SerializedName

data class CoinsData(
    @field:SerializedName("stats")
    val stats: PageStatus = PageStatus(),
    @field:SerializedName("coins")
    val coins: List<Coin> = listOf()
)

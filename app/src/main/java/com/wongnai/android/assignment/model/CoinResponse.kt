package com.wongnai.android.assignment.model

import com.google.gson.annotations.SerializedName

data class CoinResponse(
    @field:SerializedName("status")
    val status: String? = null,
    @field:SerializedName("data")
    val data: CoinData? = null
)

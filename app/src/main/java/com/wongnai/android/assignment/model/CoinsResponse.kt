package com.wongnai.android.assignment.model

import com.google.gson.annotations.SerializedName

data class CoinsResponse(
    @field:SerializedName("status")
    val status: String? = null,
    @field:SerializedName("data")
    val data: CoinsData? = null
)

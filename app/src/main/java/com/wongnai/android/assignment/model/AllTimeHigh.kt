package com.wongnai.android.assignment.model

import com.google.gson.annotations.SerializedName

data class AllTimeHigh(
    @field:SerializedName("price")
    val price: Double = 0.0,
    @field:SerializedName("timestamp")
    val timestamp: Long = 0
)

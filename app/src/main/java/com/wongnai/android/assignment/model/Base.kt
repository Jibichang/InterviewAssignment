package com.wongnai.android.assignment.model

import com.google.gson.annotations.SerializedName

data class Base(
    @field:SerializedName("sign")
    val sign: String? = null,
    @field:SerializedName("symbol")
    val symbol: String? = null
)

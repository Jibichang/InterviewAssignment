package com.wongnai.android.assignment.model

import com.google.gson.annotations.SerializedName

data class CoinResponse(
    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("data")
    var data : DataInfo
)

data class DataInfo(
    //base

    @field:SerializedName("coin")
    var coin : CoinInfo
)

data class CoinInfo(
    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("iconUrl")
    val iconUrl: String? = null,

    @field:SerializedName("price")
    val price: String? = null
)
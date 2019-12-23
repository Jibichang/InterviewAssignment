package com.wongnai.android.assignment.model

import com.google.gson.annotations.SerializedName

data class CoinResponse(
    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("data")
    var data : DataInfo
)

data class DataInfo(
    @field:SerializedName("base")
    var base : Base,

    @field:SerializedName("coin")
    var coin : CoinInfo
)

data class Base(
    @field:SerializedName("symbol")
    var symbol : String,

    @field:SerializedName("sign")
    var sign : String
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
    val price: String? = null,

    @field:SerializedName("volume")
    val volume: String? = null,

    @field:SerializedName("change")
    val change: Double = 0.0,

    @field:SerializedName("symbol")
    val symbol: String? = null,

    @field:SerializedName("allTimeHigh")
    val allTimeHigh: AllTimeHigh,

    @field:SerializedName("socials")
    val socials: MutableList<Social>,

    @field:SerializedName("firstSeen")
    val firstSeen: String? = null,

    @field:SerializedName("marketCap")
    val marketCap: String? = null,

    @field:SerializedName("numberOfMarkets")
    val numberOfMarkets: String? = null,

    @field:SerializedName("numberOfExchanges")
    val numberOfExchanges: String? = null


)

data class AllTimeHigh (
    @field:SerializedName("price")
    val price: String? = null,

    @field:SerializedName("timestamp")
    val timestamp: String? = null
)

data class Social (
    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("type")
    val type: String? = null
)

package com.wongnai.android.assignment.model

import com.google.gson.annotations.SerializedName

data class Coin(
    @field:SerializedName("id")
    val id: Long = 0,
    @field:SerializedName("slug")
    val slug: String? = null,
    @field:SerializedName("symbol")
    val symbol: String? = null,
    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("description")
    val description: String? = null,
    @field:SerializedName("color")
    val color: String? = null,
    @field:SerializedName("iconType")
    val iconType: String? = null,
    @field:SerializedName("iconUrl")
    val iconUrl: String? = null,
    @field:SerializedName("websiteUrl")
    val websiteUrl: String? = null,
    @field:SerializedName("socials")
    val socials: List<Social> = listOf(),
    @field:SerializedName("confirmedSupply")
    val confirmedSupply: Boolean = false,
    @field:SerializedName("type")
    val type: String = "coin",
    @field:SerializedName("volume")
    val volume: Long = 0,
    @field:SerializedName("marketCap")
    val marketCap: Long = 0,
    @field:SerializedName("price")
    val price: Double = 0.0,
    @field:SerializedName("circulatingSupply")
    val circulatingSupply: Double = 0.0,
    @field:SerializedName("totalSupply")
    val totalSupply: Double = 0.0,
    @field:SerializedName("firstSeen")
    val firstSeen: Long = 0,
    @field:SerializedName("change")
    val change: Double = 0.0,
    @field:SerializedName("rank")
    val rank: Int = 1,
    @field:SerializedName("numberOfMarkets")
    val numberOfMarkets: Int = 0,
    @field:SerializedName("numberOfExchanges")
    val numberOfExchanges: Int = 0,
    @field:SerializedName("history")
    val history: List<Double> = listOf(),
    @field:SerializedName("allTimeHigh")
    val allTimeHigh: AllTimeHigh? = null,
    @field:SerializedName("penalty")
    val penalty: Boolean = false
)

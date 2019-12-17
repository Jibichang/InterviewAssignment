package com.wongnai.android.assignment.model

import com.google.gson.annotations.SerializedName

data class CoinsResponse(
    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("data")
    var data : Data
)

data class Data(
    //base

    @field:SerializedName("coins")
    var coins : MutableList<Coin>
)

data class Coin(
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
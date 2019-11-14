package com.wongnai.android.assignment.model

import com.google.gson.annotations.SerializedName

data class PageStatus(
    @field:SerializedName("total")
    val total: Int = 0,
    @field:SerializedName("offset")
    val offset: Int = 0,
    @field:SerializedName("limit")
    val limit: Int = 0,
    @field:SerializedName("order")
    val order: String? = null,
    @field:SerializedName("base")
    val base: String? = null
)

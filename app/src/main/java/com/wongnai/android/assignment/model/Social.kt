package com.wongnai.android.assignment.model

import com.google.gson.annotations.SerializedName

data class Social(
    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("url")
    val url: String? = null,
    @field:SerializedName("type")
    val type: String? = null
)

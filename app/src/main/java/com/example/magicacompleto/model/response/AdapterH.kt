package com.example.magicacompleto.model.response

import com.google.gson.annotations.SerializedName

data class AdapterH(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("image")
    val image: String,
)

package com.salvador.myapplication.data.data_models

import com.google.gson.annotations.SerializedName

data class NetworkResponseModel(
    @SerializedName("items")
    val items: List<ItemModel> = listOf()
)
package com.salvador.myapplication.data.data_models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.salvador.myapplication.domain.ItemDomainModel

@Entity(tableName = "items_table")
data class ItemModel(
    @SerializedName("date")
    val date: String = "",
    @SerializedName("description")
    @PrimaryKey(autoGenerate = false)
    val description: String = "",
    @SerializedName("img")
    val img: String = "",
    @SerializedName("title")
    val title: String = "",
)

fun ItemModel.toDomain(): ItemDomainModel {
    return ItemDomainModel(
        title = title,
        date = date,
        description = description,
        img = img,
    )
}
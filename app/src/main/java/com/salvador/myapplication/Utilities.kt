package com.salvador.myapplication

import com.salvador.myapplication.domain.ItemDomainModel
import com.salvador.myapplication.ui.screens.main_screen.StatsResult

fun getTotalNumberOfItems(items: List<ItemDomainModel>?): StatsResult {

    return if (items == null || items.isEmpty()) {
        StatsResult(0f,0f)
    } else {

        val totalItems = items!!.size
        val totalItemsWithImage = items.count { it.img.isNotBlank() }
        return StatsResult(
            itemsWithImagePercent = 100f * totalItemsWithImage / items.size,
            itemsWithOutImage = 100f * (totalItems - totalItemsWithImage) / items.size
        )
    }
}
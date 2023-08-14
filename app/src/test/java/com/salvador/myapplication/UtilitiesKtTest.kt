package com.salvador.myapplication

import com.salvador.myapplication.domain.ItemDomainModel
import org.junit.Assert.*

import org.junit.Test

class UtilitiesKtTest {

    @Test
    fun getTotalNumberWithImageNullList() {
        val nullList = null
        val result = getTotalNumberOfItems(nullList)

        assertEquals(result.itemsWithImagePercent, 0f)
        assertEquals(result.itemsWithOutImage, 0f)
    }

    @Test
    fun getTotalNumberWithImageEmptyList() {
        val emptyList = emptyList<ItemDomainModel>()
        val result = getTotalNumberOfItems(emptyList)

        assertEquals(result.itemsWithImagePercent, 0f)
        assertEquals(result.itemsWithOutImage, 0f)
    }

    @Test
    fun getTotalNumberOfItemsTest() {
        val items = listOf<ItemDomainModel>(
            ItemDomainModel(title = "title1", date = "date1", description = "desc1", img = "img1"),
            ItemDomainModel(title = "title2", date = "date2", description = "desc2", img = "img2"),
            ItemDomainModel(title = "title3", date = "date3", description = "desc3", img = "img3"),
            ItemDomainModel(title = "title4", date = "date4", description = "desc4", img = "")
        )
        val result = com.salvador.myapplication.getTotalNumberOfItems(items)


        assertEquals(result.itemsWithOutImage, 25f)
        assertEquals(result.itemsWithImagePercent, 75f)

    }
}
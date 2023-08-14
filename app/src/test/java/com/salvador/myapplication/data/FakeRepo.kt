package com.salvador.myapplication.data

import com.salvador.myapplication.data.data_models.ItemModel
import com.salvador.myapplication.domain.ItemDomainModel
import com.salvador.myapplication.domain.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

class FakeRepo(): ItemRepository {
    val items = mutableListOf<ItemDomainModel>()




    override suspend fun getAllItems(): Flow<List<ItemDomainModel>> {
        return flow { items }
    }

    override suspend fun saveItems() {
        val itemDomainModel = ItemDomainModel("sal", "sal","sal","sal")
      items.add(itemDomainModel)
    }

    override suspend fun getSpecificItem(key: String): Flow<ItemDomainModel> {
        return flow{ items.find { it.date == key } }
    }
}
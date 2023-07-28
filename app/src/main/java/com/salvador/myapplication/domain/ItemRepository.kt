package com.salvador.myapplication.domain

import com.salvador.myapplication.data.data_models.ItemModel
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
   suspend fun getAllItems(): Flow<List<ItemDomainModel>>

   suspend fun saveItems()

   suspend fun getSpecificItem(key: String): Flow<ItemDomainModel>
}
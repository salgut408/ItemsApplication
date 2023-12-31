package com.salvador.myapplication.data

import android.util.Log
import com.salvador.myapplication.data.api.ApiService
import com.salvador.myapplication.data.database.ItemDao
import com.salvador.myapplication.domain.ItemDomainModel
import com.salvador.myapplication.domain.ItemRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val apiItemService: ApiService,
    private val itemDao: ItemDao,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
): ItemRepository {
    override suspend fun getAllItems(): Flow<List<ItemDomainModel>> {
        try {
            val result: Flow<List<ItemDomainModel>>
            withContext(defaultDispatcher) {
                result = itemDao.getAllItems()
            }
           return result
        } catch (e: Exception) {
            Log.e("REPOSITORY", e.stackTraceToString())
        }
        return itemDao.getAllItems()
    }

    override suspend fun saveItems() {
        withContext(defaultDispatcher) {
            val items = apiItemService.getItemsList().body()?.items
            if(!items.isNullOrEmpty()){
                itemDao.insertAll(items)
            }
        }
    }

    override suspend fun getSpecificItem(key: String): Flow<ItemDomainModel> {
        val item: Flow<ItemDomainModel>
        withContext(defaultDispatcher){
            item = itemDao.getItemById(key)
        }
        return item
    }
}
package com.salvador.myapplication.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.salvador.myapplication.data.data_models.ItemModel
import com.salvador.myapplication.domain.ItemDomainModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Query("SELECT * FROM items_table")
    fun getAllItems(): Flow<List<ItemDomainModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ItemModel>): List<Long>

    @Query("SELECT * FROM items_table WHERE description = :description LIMIT 1")
    fun getItemById(description: String): Flow<ItemDomainModel>
}
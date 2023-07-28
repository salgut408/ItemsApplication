package com.salvador.myapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.salvador.myapplication.data.data_models.ItemModel

@Database(entities = [ItemModel::class], version = 8, exportSchema = false)
 abstract class ItemDatabase: RoomDatabase() {
     abstract fun getItemDao(): ItemDao
}
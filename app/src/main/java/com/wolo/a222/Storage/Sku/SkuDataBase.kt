package com.wolo.a222.Storage.Sku

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SkuDB::class], version = 1)
abstract class SkuDataBase: RoomDatabase() {
        abstract fun skuDao(): SkuDao
        }
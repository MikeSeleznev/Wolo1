package com.wolo.a222.Storage.Sku

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [SkuDB::class], version = 1)
abstract class SkuDataBase: RoomDatabase() {
        abstract fun skuDao(): SkuDao
        }
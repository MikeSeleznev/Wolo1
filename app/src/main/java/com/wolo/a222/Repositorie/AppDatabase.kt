package com.wolo.a222.Repositorie

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(Sku::class), version = 1)
abstract class AppDatabase(): RoomDatabase() {
    abstract fun SkuDao(): SkuDao
}
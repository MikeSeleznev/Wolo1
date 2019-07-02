package com.wolo.a222.Storage.Packs

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverter
import android.arch.persistence.room.TypeConverters


@Database(entities = [PacksDB::class], version = 1)
@TypeConverters(PacksConvertor::class)
abstract class PacksDatabase: RoomDatabase() {
    abstract fun packsDao(): PacksDAO
}
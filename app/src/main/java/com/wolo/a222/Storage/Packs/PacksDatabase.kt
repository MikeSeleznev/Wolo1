package com.wolo.a222.Storage.Packs

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverter
import android.arch.persistence.room.TypeConverters
import android.arch.persistence.room.migration.Migration


@Database(entities = [PacksDB::class], version = 2)
@TypeConverters(PacksConvertor::class)
abstract class PacksDatabase: RoomDatabase() {
    abstract fun packsDao(): PacksDAO

    companion object {
        @JvmField
        val MIGRATION_1_2 = Migration1To2()

    }


}

class Migration1To2 : Migration(1,2) {
    override fun migrate(database: SupportSQLiteDatabase) {
                // 1. Create new table
        database.execSQL("ALTER TABLE PacksDB ADD COLUMN card STRING")

    }
}
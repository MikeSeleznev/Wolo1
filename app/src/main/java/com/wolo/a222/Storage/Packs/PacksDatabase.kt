package com.wolo.a222.Storage.Packs

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.migration.Migration


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
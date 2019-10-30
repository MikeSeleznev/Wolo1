package com.wolo.a222.feature.common.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.wolo.a222.feature.common.entity.Pack
import com.wolo.a222.feature.common.entity.Purchases
import com.wolo.a222.feature.common.entity.SkuDeck
import com.wolo.a222.feature.common.model.PackDao
import com.wolo.a222.feature.common.model.PurchasesDao
import com.wolo.a222.feature.common.model.SkuDeckDao

@Database(entities = [Pack::class, SkuDeck::class, Purchases::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WoloDatabase: RoomDatabase(){
    abstract fun PackDao(): PackDao
    abstract fun SkuDeckDao(): SkuDeckDao
    abstract fun PurchasesDao(): PurchasesDao
}

private const val LIST_ITEM_SEPARATOR = "|"

class Converters {

    @TypeConverter
    fun listOfStringToString(tasks: List<String>): String = tasks.joinToString(LIST_ITEM_SEPARATOR)

    @TypeConverter
    fun stringTolistOfString(value: String): List<String> {
        if (value.isBlank()) {
            return emptyList()
        }
        val values = value.split(LIST_ITEM_SEPARATOR)
        return if (values.isEmpty()) emptyList() else values.map { it }
    }

}
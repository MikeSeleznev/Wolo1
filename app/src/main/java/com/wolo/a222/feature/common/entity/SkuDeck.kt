package com.wolo.a222.feature.common.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.wolo.a222.feature.common.model.SkuDeckDao
import com.wolo.a222.feature.common.storage.Converters


@Entity(tableName = SkuDeckDao.TABLE)
@TypeConverters(Converters::class)
data class SkuDeck(
        @PrimaryKey
        var skuType: String,
        var name: String,
        var price: String)
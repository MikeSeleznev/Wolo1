package com.wolo.a222.feature.common.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.wolo.a222.feature.common.model.PurchasesDao
import com.wolo.a222.feature.common.storage.Converters

@Entity(tableName = PurchasesDao.TABLE)
@TypeConverters(Converters::class)
data class Purchases(
    @PrimaryKey
    var id: String = ""
)
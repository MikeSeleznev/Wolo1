package com.wolo.a222.Storage.Sku

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
open class SkuDB {

    @PrimaryKey
    var id: Long = 0

    lateinit var name: String
}
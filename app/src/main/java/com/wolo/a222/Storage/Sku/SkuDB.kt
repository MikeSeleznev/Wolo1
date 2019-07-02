package com.wolo.a222.Storage.Sku

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
open class SkuDB {

    @PrimaryKey
    var id: Long = 0

    lateinit var name: String
}
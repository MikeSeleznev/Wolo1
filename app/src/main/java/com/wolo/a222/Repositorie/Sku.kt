package com.wolo.a222.Repositorie

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "database")
data class Sku(

    @ColumnInfo(name = "sku")

    @PrimaryKey
    val name: String

    //private var id: Long = 0


)

{constructor():this("")}

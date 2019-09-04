package com.wolo.a222.Storage.Packs

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
open class PacksDB {

    @PrimaryKey
    var name: String = ""

    var id: String = ""

    lateinit var card: ArrayList<String>

}
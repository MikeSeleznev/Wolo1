package com.wolo.a222.Storage.Packs

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverter
import com.wolo.a222.Cards

@Entity
open class PacksDB {

    @PrimaryKey
    var name: String = ""

    var id: String = ""

    lateinit var card: ArrayList<String>

}
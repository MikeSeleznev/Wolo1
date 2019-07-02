package com.wolo.a222.Storage.Packs

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query


@Dao
interface PacksDAO {

    @Query("Select * From PacksDB")
    fun getAll(): List<PacksDB>

    @Insert
    fun insert(packsDB: PacksDB)

}
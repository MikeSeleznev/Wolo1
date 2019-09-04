package com.wolo.a222.Storage.Packs

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface PacksDAO {

    @Query("Select * From PacksDB")
    fun getAll(): List<PacksDB>

    @Insert
    fun insert(packsDB: PacksDB)

}
package com.wolo.a222.Repositorie

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query

@Dao
interface SkuDao {

    @Query("SELECT * FROM Sku")
    fun getAll(): List<Sku>

    @Query("SELECT * FROM Sku WHERE id = :id")
    fun getById(id: Long)

    fun insert(sku: Sku)

    fun update(sku: Sku)

    fun delete(sku: Sku)
}
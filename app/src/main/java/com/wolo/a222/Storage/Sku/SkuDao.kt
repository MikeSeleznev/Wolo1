package com.wolo.a222.Storage.Sku

import android.arch.persistence.room.*

@Dao
interface SkuDao {

    @Query("Select * From SkuDB")
    fun getAll(): List<SkuDB>

    @Insert
    fun insert(skuDB: SkuDB)

    /*@Delete
    fun delete()

    @Update
    fun update()*/

}
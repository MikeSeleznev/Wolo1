package com.wolo.a222.feature.common.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wolo.a222.feature.common.entity.Pack
import com.wolo.a222.feature.common.entity.Purchases
import io.reactivex.Flowable

@Dao
interface PurchasesDao{

    companion object{
        const val TABLE = "purchases"
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(packs: List<Purchases>): List<Long>

    @Query("DELETE FROM $TABLE")
    fun deleteAll(): Int

    @Query(
        "SELECT * FROM $TABLE "
    )
    fun getPurchases(): Flowable<List<Purchases>>
}
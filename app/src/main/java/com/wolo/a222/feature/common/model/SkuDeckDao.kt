package com.wolo.a222.feature.common.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wolo.a222.feature.common.entity.SkuDeck
import io.reactivex.Flowable

@Dao
interface SkuDeckDao{

    companion object{
        const val TABLE = "skudeck"
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(packs: List<SkuDeck>): List<Long>

    @Query("DELETE FROM $TABLE")
    fun deleteAll(): Int

    @Query(
        "SELECT * FROM $TABLE "
    )
    fun getSkuDecks(): Flowable<List<SkuDeck>>
}
package com.wolo.a222.feature.common.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wolo.a222.feature.common.entity.Pack
import io.reactivex.Flowable

@Dao
interface PackDao{

    companion object{
        const val TABLE = "packs"
        const val EN_TASKS = "enTasks"
        const val EN_NAME = "enName"
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(packs: List<Pack>): List<Long>

    @Query("DELETE FROM $TABLE")
    fun deleteAll(): Int

    @Query(
        "SELECT * FROM $TABLE "
    )
    fun getPacks(): Flowable<List<Pack>>

}
package com.wolo.a222.feature.common.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.wolo.a222.feature.common.model.PackDao
import com.wolo.a222.feature.common.storage.Converters

@Entity(tableName = PackDao.TABLE)
@TypeConverters(Converters::class)
data class Pack(
    @PrimaryKey
    var id: String = "",
    var name: String = "",
    var tasks: List<String> = emptyList(),
    var paid: Boolean = false,
    var activeImage: String = "",
    var nonActiveImage: String = "",
    var priority: Long = 0L,
    var restTasks: Int = 1,
    var alwaysActive: Boolean = false,
    @ColumnInfo(name = PackDao.EN_TASKS)
    var enTasks: List<String> = emptyList(),
    var enName: String = ""
)
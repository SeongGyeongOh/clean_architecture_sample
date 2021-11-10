package com.gyeong.architecturekotlin.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cnt_table")
class CntEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "cnt") val cnt: Int
)

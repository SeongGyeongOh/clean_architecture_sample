package com.example.architecturekotlin.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Entity(tableName = "cnt_table")
class CntEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "cnt") val cnt: Int
)

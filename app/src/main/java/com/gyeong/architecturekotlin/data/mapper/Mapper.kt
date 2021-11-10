package com.gyeong.architecturekotlin.data.mapper

import com.gyeong.architecturekotlin.data.entity.CntEntity
import com.gyeong.architecturekotlin.data.entity.TodoEntity
import com.gyeong.architecturekotlin.data.entity.WalkEntity
import com.gyeong.architecturekotlin.domain.model.CntModel
import com.gyeong.architecturekotlin.domain.model.TodoModel
import com.gyeong.architecturekotlin.domain.model.WalkModel


fun CntModel.map() = CntEntity(
    id = 0,
    cnt = count
)

fun CntEntity.map() = CntModel(
    count = cnt
)

fun TodoEntity.map() = TodoModel(
    id = id,
    title = title
)

fun WalkEntity.map() = WalkModel(
    id = id,
    date = date,
    hour = hour,
    count = count
)

fun WalkModel.map() = WalkEntity(
    id = id,
    date = date,
    hour = hour,
    count = count
)
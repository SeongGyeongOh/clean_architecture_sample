package com.gyeong.architecturekotlin.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class TodoModel (
    val id: Int = 0,
    val title: String = ""
)
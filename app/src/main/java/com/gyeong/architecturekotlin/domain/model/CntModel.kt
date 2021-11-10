package com.gyeong.architecturekotlin.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CntModel(
    val count: Int = 0
)
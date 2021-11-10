package com.gyeong.architecturekotlin.domain.repository.local

import com.gyeong.architecturekotlin.domain.model.CntModel

interface CntLocalRepository {

    suspend fun getLatestCnt(): List<CntModel>
    suspend fun saveCount(cnt: CntModel): Long
}
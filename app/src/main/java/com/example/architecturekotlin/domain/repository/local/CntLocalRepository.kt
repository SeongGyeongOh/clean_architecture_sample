package com.example.architecturekotlin.domain.repository.local

import androidx.lifecycle.LiveData
import com.example.architecturekotlin.data.entity.CntEntity
import com.example.architecturekotlin.domain.model.CntModel
import kotlinx.coroutines.flow.Flow

interface CntLocalRepository {

    suspend fun getLatestCnt(): List<CntModel>
    suspend fun saveCount(cnt: CntModel): Long
}
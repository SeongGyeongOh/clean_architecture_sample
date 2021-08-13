package com.example.architecturekotlin.data.repository.local

import com.example.architecturekotlin.data.db.CntDao
import com.example.architecturekotlin.data.mapper.map
import com.example.architecturekotlin.domain.model.CntModel
import com.example.architecturekotlin.domain.repository.local.CntLocalRepository
import com.example.architecturekotlin.util.common.Logger
import javax.inject.Inject

class CntLocalRepositoryImpl @Inject constructor(
    private val cntDao: CntDao
): CntLocalRepository {

    override suspend fun getLatestCnt(): List<CntModel> {
        return cntDao.getLatestCnt().map { cntEntity ->
            cntEntity.map()
        }
    }

    override suspend fun saveCount(cnt: CntModel): Long {
        return cntDao.saveCnt(cnt = cnt.map())
    }
}
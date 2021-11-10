package com.gyeong.architecturekotlin.data.repository.local

import com.gyeong.architecturekotlin.data.db.CntDao
import com.gyeong.architecturekotlin.data.mapper.map
import com.gyeong.architecturekotlin.domain.model.CntModel
import com.gyeong.architecturekotlin.domain.repository.local.CntLocalRepository
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
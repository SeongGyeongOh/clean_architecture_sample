package com.gyeong.architecturekotlin.domain.usecase

import com.gyeong.architecturekotlin.domain.model.CntModel
import com.gyeong.architecturekotlin.domain.repository.local.CntLocalRepository
import javax.inject.Inject

class SaveCntUseCase @Inject constructor(
    private val cntLocalRepository: CntLocalRepository
): UseCaseWithParams<Long, Int>() {

    public override suspend fun buildUseCase(params: Int): Long {
        return cntLocalRepository.saveCount(CntModel(count = params))
    }
}
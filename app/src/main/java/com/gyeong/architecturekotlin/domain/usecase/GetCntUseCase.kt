package com.gyeong.architecturekotlin.domain.usecase

import com.gyeong.architecturekotlin.domain.model.CntModel
import com.gyeong.architecturekotlin.domain.repository.local.CntLocalRepository
import javax.inject.Inject

class GetCntUseCase @Inject constructor(
    private val cntLocalRepository: CntLocalRepository
): UseCaseWithoutParams<CntModel>() {

    public override suspend fun buildUseCase(): CntModel {
        return if (cntLocalRepository.getLatestCnt().isNotEmpty()) {
            cntLocalRepository.getLatestCnt()[0]
        } else {
            CntModel()
        }
    }
}
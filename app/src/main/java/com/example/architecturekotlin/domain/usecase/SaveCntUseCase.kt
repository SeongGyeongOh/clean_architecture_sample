package com.example.architecturekotlin.domain.usecase

import com.example.architecturekotlin.domain.model.CntModel
import com.example.architecturekotlin.domain.repository.local.CntLocalRepository
import javax.inject.Inject

class SaveCntUseCase @Inject constructor(
    private val cntLocalRepository: CntLocalRepository
): UseCaseWithParams<Long, Int>() {

    public override suspend fun buildUseCase(params: Int): Long {
        return cntLocalRepository.saveCount(CntModel(count = params))
    }
}
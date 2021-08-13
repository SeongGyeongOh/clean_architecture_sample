package com.example.architecturekotlin.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.architecturekotlin.domain.model.CntModel
import com.example.architecturekotlin.domain.repository.local.CntLocalRepository
import com.example.architecturekotlin.util.common.Logger
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
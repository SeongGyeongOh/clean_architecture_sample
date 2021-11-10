package com.gyeong.architecturekotlin.domain.usecase

import com.gyeong.architecturekotlin.domain.model.WalkModel
import com.gyeong.architecturekotlin.domain.repository.local.WalkRepository
import javax.inject.Inject

class GetWalkUseCase @Inject constructor(
    private val repository: WalkRepository
) : UseCaseWithoutParams<List<WalkModel>>() {

    public override suspend fun buildUseCase(): List<WalkModel> {
        return repository.getWalkCount()
    }
}
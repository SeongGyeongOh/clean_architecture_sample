package com.gyeong.architecturekotlin.domain.usecase

import com.gyeong.architecturekotlin.domain.model.WalkModel
import com.gyeong.architecturekotlin.domain.repository.local.WalkRepository
import javax.inject.Inject

class UpsertWalkUseCase @Inject constructor(
    private val walkRepository: WalkRepository
) : UseCaseWithParams<Unit, WalkModel>() {
    public override suspend fun buildUseCase(params: WalkModel) {
//        Logger.d("유스케이스 확인 ${params.date}, ${params.count}")
        return walkRepository.upsertWalk(params)
    }
}
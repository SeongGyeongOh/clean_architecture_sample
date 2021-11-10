package com.gyeong.architecturekotlin.domain.usecase

//class SaveWalkUseCase @Inject constructor(
//    private val walkRepository: WalkRepository
//) : UseCaseWithParams<List<WalkModel>, Pair<String, Int>>(){
//
//    public override suspend fun buildUseCase(params: Pair<String, Int>): Flow<List<WalkModel>> {
//        walkRepository.insertWalkCount(
//            WalkModel(
//                date = params.first,
//                count = params.second)
//        )
//
//        return walkRepository.getWalkCount()
//    }
//}
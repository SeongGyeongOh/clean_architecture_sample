package com.gyeong.architecturekotlin.domain.usecase

import com.gyeong.architecturekotlin.data.mapper.map
import com.gyeong.architecturekotlin.domain.model.TodoModel
import com.gyeong.architecturekotlin.domain.repository.remote.TodoRemoteRepository
import javax.inject.Inject

class GetTodoDetailUseCase @Inject constructor(
    private val todoRemoteRepository: TodoRemoteRepository
) : UseCaseWithParams<TodoModel, Int>(){
    public override suspend fun buildUseCase(params: Int): TodoModel {
        return todoRemoteRepository.getTodoDetail(params).map()
    }
}
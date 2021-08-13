package com.example.architecturekotlin.domain.usecase

import com.example.architecturekotlin.data.mapper.map
import com.example.architecturekotlin.domain.model.TodoModel
import com.example.architecturekotlin.domain.repository.remote.TodoRemoteRepository
import javax.inject.Inject

class GetTodoDetailUseCase @Inject constructor(
    private val todoRemoteRepository: TodoRemoteRepository
) : UseCaseWithParams<TodoModel, Int>(){
    public override suspend fun buildUseCase(params: Int): TodoModel {
        return todoRemoteRepository.getTodoDetail(params).map()
    }
}
package com.example.architecturekotlin.domain.usecase

import com.example.architecturekotlin.data.mapper.map
import com.example.architecturekotlin.domain.model.TodoModel
import com.example.architecturekotlin.domain.repository.remote.TodoRemoteRepository
import javax.inject.Inject


class GetTodosUseCase @Inject constructor(
    private val todoRemoteRepository: TodoRemoteRepository
): UseCaseWithoutParams<List<TodoModel>>() {

    public override suspend fun buildUseCase(): List<TodoModel> {
        return todoRemoteRepository.getTodos().map {
            it.map()
        }
    }
}
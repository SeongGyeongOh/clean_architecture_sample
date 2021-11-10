package com.gyeong.architecturekotlin.domain.usecase

import com.gyeong.architecturekotlin.data.mapper.map
import com.gyeong.architecturekotlin.domain.model.TodoModel
import com.gyeong.architecturekotlin.domain.repository.remote.TodoRemoteRepository
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
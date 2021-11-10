package com.gyeong.architecturekotlin.data.repository.remote

import com.gyeong.architecturekotlin.data.api.TodoApi
import com.gyeong.architecturekotlin.data.entity.TodoEntity
import com.gyeong.architecturekotlin.domain.repository.remote.TodoRemoteRepository
import javax.inject.Inject

class TodoRemoteRepositoryImpl @Inject constructor(
    private val todoApi: TodoApi
): TodoRemoteRepository {

    override suspend fun getTodos(): List<TodoEntity> {
        return todoApi.getTodos()
    }

    override suspend fun getTodoDetail(id: Int): TodoEntity {
        return todoApi.getTodoDetail(id)
    }
}
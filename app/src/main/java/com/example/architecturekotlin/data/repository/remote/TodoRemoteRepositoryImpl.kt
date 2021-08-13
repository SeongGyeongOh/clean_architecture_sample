package com.example.architecturekotlin.data.repository.remote

import com.example.architecturekotlin.data.api.TodoApi
import com.example.architecturekotlin.data.entity.TodoEntity
import com.example.architecturekotlin.domain.repository.remote.TodoRemoteRepository
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
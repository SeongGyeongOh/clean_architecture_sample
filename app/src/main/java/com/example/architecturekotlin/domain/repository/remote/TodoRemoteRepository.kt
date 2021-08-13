package com.example.architecturekotlin.domain.repository.remote

import com.example.architecturekotlin.data.entity.TodoEntity

interface TodoRemoteRepository {
    suspend fun getTodos(): List<TodoEntity>
    suspend fun getTodoDetail(id: Int): TodoEntity
}
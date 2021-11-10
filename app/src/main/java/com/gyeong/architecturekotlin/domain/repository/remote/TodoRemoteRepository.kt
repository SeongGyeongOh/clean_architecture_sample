package com.gyeong.architecturekotlin.domain.repository.remote

import com.gyeong.architecturekotlin.data.entity.TodoEntity

interface TodoRemoteRepository {
    suspend fun getTodos(): List<TodoEntity>
    suspend fun getTodoDetail(id: Int): TodoEntity
}
package com.gyeong.architecturekotlin.data.api

import com.gyeong.architecturekotlin.data.entity.TodoEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface TodoApi {

    @GET("todos")
    suspend fun getTodos(): List<TodoEntity>

    @GET("todos/{id}")
    suspend fun getTodoDetail(@Path("id") id: Int): TodoEntity
}
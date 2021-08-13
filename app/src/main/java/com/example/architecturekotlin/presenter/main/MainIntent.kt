package com.example.architecturekotlin.presenter.main

import com.example.architecturekotlin.domain.model.TodoModel

sealed class MainIntent {
    object GetTodos : MainIntent()
    data class GetTodoDetail(val id: Int) : MainIntent()
    data class SendTodoDetail(val todo: TodoModel) : MainIntent()
    object GetCnt : MainIntent()
    data class SaveCnt(val cnt: Int) : MainIntent()
}
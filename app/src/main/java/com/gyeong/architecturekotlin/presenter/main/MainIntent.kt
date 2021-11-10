package com.gyeong.architecturekotlin.presenter.main

import com.gyeong.architecturekotlin.domain.model.TodoModel

sealed class MainIntent {
    object GetTodos : MainIntent()
    data class GetTodoDetail(val id: Int) : MainIntent()
    data class SendTodoDetail(val todo: TodoModel) : MainIntent()
    object GetCnt : MainIntent()
    data class SaveCnt(val cnt: Int) : MainIntent()
}
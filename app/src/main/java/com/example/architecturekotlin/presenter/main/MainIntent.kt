package com.example.architecturekotlin.presenter.main

sealed class MainIntent {
    object GetTodos: MainIntent()
    data class GetTodoDetail(val id:
                             Int): MainIntent()
    object GetCnt: MainIntent()
    data class SaveCnt(val cnt: Int): MainIntent()
}
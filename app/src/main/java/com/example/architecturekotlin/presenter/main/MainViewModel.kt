package com.example.architecturekotlin.presenter.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecturekotlin.domain.model.TodoModel
import com.example.architecturekotlin.domain.usecase.GetCntUseCase
import com.example.architecturekotlin.domain.usecase.GetTodoDetailUseCase
import com.example.architecturekotlin.domain.usecase.GetTodosUseCase
import com.example.architecturekotlin.domain.usecase.SaveCntUseCase
import com.example.architecturekotlin.util.common.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var getTodosUseCase: GetTodosUseCase,
    private var getCntUseCase: GetCntUseCase,
    private var saveCntUseCase: SaveCntUseCase,
    private var getTodoDetailUseCase: GetTodoDetailUseCase
): ViewModel() {

    var _state = MutableStateFlow<MainState>(MainState.Idle)
    val state: StateFlow<MainState>
        get() = _state

    var _intent = MutableSharedFlow<MainIntent>()
    val intent: SharedFlow<MainIntent>
        get() = _intent

    val _todo = MutableLiveData<TodoModel>()
    val todo: LiveData<TodoModel> get() = _todo

    init {
        handleIntent()
    }

    fun setIntent(intent: MainIntent) {
        viewModelScope.launch { _intent.emit(intent) }
    }

    fun handleIntent() = viewModelScope.launch {
        intent.collect {
            when (it) {
                MainIntent.GetTodos -> {
                    getTodos()
                }

                is MainIntent.GetTodoDetail -> {
                    getTodoDetail(it.id)
                }
                MainIntent.GetCnt -> {
                    getCnt()
                }
                is MainIntent.SendTodoDetail -> {
                    _todo.value = it.todo
                }
                is MainIntent.SaveCnt -> {
                    saveCnt(it.cnt)
                }
            }
        }
    }

    suspend fun getTodos() {
        _state.value = try {
            val todo = getTodosUseCase.buildUseCase()
            Log.d("asd", "todo : $todo")
            MainState.Todos.Success(todo)
        } catch (e: Exception) {
            MainState.Todos.Fail(Error("TODO 리스트를 가져오는데 실패하였습니다.", e.cause))
        }
    }

    suspend fun getTodoDetail(id: Int) {
        _state.value = try {
            MainState.TodoDetail.Success(
                getTodoDetailUseCase.buildUseCase(id)
            )
        } catch (e: Exception) {
            MainState.TodoDetail.Fail(Error("TODO 디테일을 가져오는데 실패하였습니다.", e.cause))
        }
    }

    suspend fun getCnt() {
        _state.value = try {
            val cnt = getCntUseCase.buildUseCase()
            MainState.GetCnt.Success(cnt)
        } catch (e: Exception) {
            Logger.e("e message: ${e.message}")
            MainState.GetCnt.Fail(Error("", e.cause))
        }
    }

    suspend fun saveCnt(cnt: Int) {
        _state.value = try {
            val cnt = saveCntUseCase.buildUseCase(cnt)
            if (cnt > 0) {
                MainState.SaveCnt.Success(cnt)
            } else {
                MainState.SaveCnt.Fail(Error("fail insert cnt", Throwable("fail insert cnt")))
            }
        } catch (e: Exception) {
            Logger.e("e message: ${e.message}")
            MainState.SaveCnt.Fail(Error("", e.cause))
        }
    }
}
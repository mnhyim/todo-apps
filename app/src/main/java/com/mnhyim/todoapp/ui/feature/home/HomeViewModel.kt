package com.mnhyim.todoapp.ui.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnhyim.todoapp.domain.model.Resource
import com.mnhyim.todoapp.domain.model.Todo
import com.mnhyim.todoapp.domain.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    /* TODO: Should probably use UseCase or something here instead */
    private val repository: TodoRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    val todos = repository.todos.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    init {
        fetchTodos()
    }

    /*TODO: NOT USED*/
    private fun getTodos() {
        repository.getTodoss().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update { it.copy(isLoading = false) }
                    Log.d("HomeViewModel", "Error ${result.exception}")
                }
                is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                is Resource.Success<List<Todo>> -> {
                    _state.update { it.copy(isLoading = false) }
                    _state.update { it.copy(todos = result.data) }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun checkItem(todo: Todo) {
        viewModelScope.launch {
            repository.updateTodo(todo.copy(completed = !todo.completed))
        }
    }

    fun fetchTodos() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            repository.refreshTodos()
            _state.update { it.copy(isLoading = false) }
        }
    }
}
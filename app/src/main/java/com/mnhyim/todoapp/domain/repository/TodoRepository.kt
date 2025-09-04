package com.mnhyim.todoapp.domain.repository

import com.mnhyim.todoapp.domain.model.Resource
import com.mnhyim.todoapp.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    val todos: Flow<List<Todo>>
    fun getTodoss(): Flow<Resource<List<Todo>>>
    fun insertTodos(todos: Todo)
    suspend fun updateTodo(todo: Todo)

    suspend fun fetchTodos()
}
package com.mnhyim.todoapp.domain.repository

import com.mnhyim.todoapp.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    fun getTodos(): Flow<List<Todo>>
    fun insertTodos(todos: Todo)
}
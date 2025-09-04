package com.mnhyim.todoapp.domain.repository

import com.mnhyim.todoapp.domain.model.Resource
import com.mnhyim.todoapp.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    fun getTodos(): Flow<Resource<List<Todo>>>
    fun insertTodos(todos: Todo)
    fun updateTodo(todo: Todo)
}
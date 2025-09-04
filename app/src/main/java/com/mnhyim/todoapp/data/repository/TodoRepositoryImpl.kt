package com.mnhyim.todoapp.data.repository

import com.mnhyim.todoapp.data.datasource.local.AppDao
import com.mnhyim.todoapp.domain.model.Todo
import com.mnhyim.todoapp.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    private val dao: AppDao
): TodoRepository {

    override fun getTodos(): Flow<List<Todo>> {
        TODO("Not yet implemented")
    }

    override fun insertTodos(todos: Todo) {
        TODO("Not yet implemented")
    }
}
package com.mnhyim.todoapp.data.repository

import com.mnhyim.todoapp.data.datasource.local.AppDao
import com.mnhyim.todoapp.data.datasource.remote.TodoApiService
import com.mnhyim.todoapp.domain.model.Resource
import com.mnhyim.todoapp.domain.model.Todo
import com.mnhyim.todoapp.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TodoRepositoryImpl(
    private val dao: AppDao,
    private val api: TodoApiService
) : TodoRepository {


    override fun getTodos(): Flow<Resource<List<Todo>>> = flow {
        emit(Resource.Loading)
        try {
            val result = api.getTodos().map { todo ->
                Todo(
                    userId = todo.userId,
                    id = todo.id,
                    title = todo.title,
                    completed = todo.completed
                )
            }
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }

    override fun insertTodos(todos: Todo) {
        TODO("Not yet implemented")
    }

    override fun updateTodo(todo: Todo) {
        TODO("Not yet implemented")
    }

}
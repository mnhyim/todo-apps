package com.mnhyim.todoapp.data.repository

import android.util.Log
import com.mnhyim.todoapp.data.datasource.local.AppDao
import com.mnhyim.todoapp.data.datasource.remote.TodoApiService
import com.mnhyim.todoapp.data.entity.TodoEntity
import com.mnhyim.todoapp.domain.model.Resource
import com.mnhyim.todoapp.domain.model.Todo
import com.mnhyim.todoapp.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class TodoRepositoryImpl(
    private val dao: AppDao,
    private val api: TodoApiService
) : TodoRepository {

    override val todos: Flow<List<Todo>> = dao.getTodos().map { todos ->
        todos.map { todo ->
            Todo(
                userId = todo.userId,
                id = todo.id,
                title = todo.title,
                completed = todo.completed
            )
        }
    }

    override suspend fun refreshTodos() {
        try {
            val result = api.getTodos()
            result.forEach {
                dao.insertTodos(it)
            }
        } catch (e: Exception) {
            Log.d("TodoReposImpl", "$e")
        }
    }

    /*TODO: NOT USED*/
    override fun getTodoss(): Flow<Resource<List<Todo>>> = flow {
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

    override suspend fun updateTodo(todo: Todo) {
        val todoEntity = TodoEntity(
            userId = todo.userId,
            id = todo.id,
            title = todo.title,
            completed = todo.completed
        )
        dao.updateTodo(todoEntity)
    }
}
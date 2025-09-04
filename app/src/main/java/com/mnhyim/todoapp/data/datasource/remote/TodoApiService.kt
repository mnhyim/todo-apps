package com.mnhyim.todoapp.data.datasource.remote

import com.mnhyim.todoapp.data.dto.TodoResponse
import com.mnhyim.todoapp.data.entity.TodoEntity
import com.mnhyim.todoapp.domain.model.Todo
import retrofit2.http.GET

interface TodoApiService {

    @GET("todos")
    suspend fun getTodos(): List<TodoEntity>
}
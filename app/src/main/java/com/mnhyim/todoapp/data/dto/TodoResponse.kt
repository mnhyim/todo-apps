package com.mnhyim.todoapp.data.dto

data class TodoResponse(
    val todos: List<TodoDto>
)

data class TodoDto(
    val userId: Long,
    val id: Long,
    val title: String,
    val completed: Boolean
)

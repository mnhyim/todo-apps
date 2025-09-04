package com.mnhyim.todoapp.ui.feature.home

import com.mnhyim.todoapp.domain.model.Todo

data class HomeUiState(
    val isLoading: Boolean = false,
    val todos: List<Todo> = emptyList(),
)
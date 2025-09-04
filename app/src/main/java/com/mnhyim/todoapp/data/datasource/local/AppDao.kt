package com.mnhyim.todoapp.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mnhyim.todoapp.data.entity.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Query("SELECT * FROM todos")
    fun getTodos(): Flow<List<TodoEntity>>

    @Insert
    fun insertTodos(vararg todos: TodoEntity)
}
package com.mnhyim.todoapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("todos")
data class TodoEntity(
    val userId: Long,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val completed: Boolean
)

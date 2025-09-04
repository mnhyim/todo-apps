package com.mnhyim.todoapp.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mnhyim.todoapp.data.entity.TodoEntity

@Database(
    entities = [TodoEntity::class],
    version = 1,
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun appDao(): AppDao
}
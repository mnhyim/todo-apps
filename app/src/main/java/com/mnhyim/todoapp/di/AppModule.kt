package com.mnhyim.todoapp.di

import androidx.room.Room
import com.mnhyim.todoapp.data.datasource.local.AppDatabase
import com.mnhyim.todoapp.data.repository.TodoRepositoryImpl
import com.mnhyim.todoapp.domain.repository.TodoRepository
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            klass = AppDatabase::class.java,
            "app-db"
        ).build()
    }

    single { get<AppDatabase>().appDao() }
    single<TodoRepository> { TodoRepositoryImpl(get()) }
}
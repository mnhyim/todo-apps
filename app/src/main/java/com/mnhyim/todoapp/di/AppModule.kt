package com.mnhyim.todoapp.di

import androidx.room.Room
import com.mnhyim.todoapp.data.datasource.local.AppDatabase
import com.mnhyim.todoapp.data.datasource.remote.TodoApiService
import com.mnhyim.todoapp.data.repository.TodoRepositoryImpl
import com.mnhyim.todoapp.domain.repository.TodoRepository
import com.mnhyim.todoapp.ui.feature.home.HomeViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            klass = AppDatabase::class.java,
            "app-db"
        ).build()
    }

    single { get<AppDatabase>().appDao() }
    single<TodoRepository> { TodoRepositoryImpl(get(), get()) }
}

/* NETWORK DI STUFF */
fun provideGsonFactory() = GsonConverterFactory.create()
fun provideRetrofit(gsonFactory: GsonConverterFactory): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(gsonFactory)
        .build()
}
fun provideApi(retrofit: Retrofit): TodoApiService {
    return retrofit.create(TodoApiService::class.java)
}

val networkModule = module {
    single { provideGsonFactory() }
    single { provideRetrofit(get())}
    single { provideApi(get()) }
}

val viewModelsModule = module {
    singleOf(::HomeViewModel)
}
package com.mnhyim.todoapp

import android.app.Application
import com.mnhyim.todoapp.di.databaseModule
import com.mnhyim.todoapp.di.networkModule
import com.mnhyim.todoapp.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApp)
            modules(
                databaseModule,
                networkModule,
                viewModelsModule
            )
        }
    }
}
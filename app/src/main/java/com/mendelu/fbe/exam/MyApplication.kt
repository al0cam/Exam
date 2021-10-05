package com.mendelu.fbe.exam

import android.app.Application
import android.content.Context
import cz.mendelu.fbe.mytodo.di.daoModule
import cz.mendelu.fbe.mytodo.di.databaseModule
import cz.mendelu.fbe.mytodo.di.repositoryModule
import cz.mendelu.fbe.mytodo.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {

        appContext = applicationContext

        super.onCreate()
        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(this@MyApplication)
            // declare modules
            modules(daoModule, databaseModule, repositoryModule, viewModelModule)
        }
    }
    companion object{
        lateinit var appContext: Context
            private set
    }
}
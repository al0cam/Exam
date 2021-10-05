package cz.mendelu.fbe.mytodo.di

import com.mendelu.fbe.exam.MyApplication
import com.mendelu.fbe.termoverview.database.RideDatabase
import org.koin.dsl.module

val databaseModule = module {
    fun provideDatabase():RideDatabase = RideDatabase.getDatabase(MyApplication.appContext)
    single {
        provideDatabase()
    }
}
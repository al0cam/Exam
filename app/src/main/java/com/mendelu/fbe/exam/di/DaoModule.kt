package cz.mendelu.fbe.mytodo.di

import com.mendelu.fbe.termoverview.database.RideDao
import com.mendelu.fbe.termoverview.database.RideDatabase
import org.koin.dsl.module

val daoModule = module {

    fun provideTasksDao(database: RideDatabase): RideDao = database.expenseDao()

    single {
        provideTasksDao(get())
    }
}
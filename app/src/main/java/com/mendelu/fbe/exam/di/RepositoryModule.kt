package cz.mendelu.fbe.mytodo.di

import com.mendelu.fbe.termoverview.database.RideDao
import com.mendelu.fbe.termoverview.database.RideLocalRepositoryImpl
import com.mendelu.fbe.termoverview.database.IRideLocalRepository
import org.koin.dsl.module

val repositoryModule = module {

    fun provideTaskLocalRepository (dao: RideDao) : IRideLocalRepository{
        return RideLocalRepositoryImpl(dao)
    }

    single{
        provideTaskLocalRepository(get())
    }
}
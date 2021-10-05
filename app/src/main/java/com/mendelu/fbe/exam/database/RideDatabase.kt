package com.mendelu.fbe.termoverview.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase



@Database(entities = [Ride::class], version = 3, exportSchema = true)
abstract class RideDatabase : RoomDatabase(){

    abstract fun expenseDao(): RideDao

    companion object {

        private var INSTANCE: RideDatabase? = null

        fun getDatabase(context: Context): RideDatabase {
            if (INSTANCE == null) {
                synchronized(RideDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            RideDatabase::class.java, "expenses_database"
                        ).fallbackToDestructiveMigration().addMigrations(MIGRATION_1_2).build()
                    }
                }
            }
            return INSTANCE!!
        }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE tasks ADD COLUMN date INTEGER")
            }

        }
    }
}
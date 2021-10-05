package com.mendelu.fbe.termoverview.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RideDao {

    // All tasks
    @Query("SELECT * FROM ride")
    fun getAll(): LiveData<MutableList<Ride>>

    // Single task
    @Query("SELECT * FROM ride WHERE id = :id")
    suspend fun findById(id: Long): Ride

    // Insert
    @Insert
    suspend fun insert(ride: Ride): Long


    // Delete
    @Delete
    suspend fun delete(ride: Ride)

    // Update
    @Update
    suspend fun update(ride: Ride)
}
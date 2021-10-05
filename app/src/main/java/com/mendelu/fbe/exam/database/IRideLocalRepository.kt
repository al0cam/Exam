package com.mendelu.fbe.termoverview.database

import androidx.lifecycle.LiveData



interface IRideLocalRepository {
    fun getAll(): LiveData<MutableList<Ride>>
    suspend fun findById(id: Long): Ride
    suspend fun insert(ride: Ride): Long
    suspend fun delete(ride: Ride)
    suspend fun update(ride: Ride)
}
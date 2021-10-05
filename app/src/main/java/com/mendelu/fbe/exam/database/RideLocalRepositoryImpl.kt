package com.mendelu.fbe.termoverview.database

import androidx.lifecycle.LiveData

class RideLocalRepositoryImpl(private val rideDao: RideDao) : IRideLocalRepository {


    override fun getAll(): LiveData<MutableList<Ride>> {
        return rideDao.getAll()
    }

    override suspend fun findById(id: Long): Ride {
        return rideDao.findById(id)
    }

    override suspend fun insert(ride: Ride): Long {
        return rideDao.insert(ride)
    }

    override suspend fun delete(ride: Ride) {
        rideDao.delete(ride)
    }

    override suspend fun update(ride: Ride) {
        rideDao.update(ride)
    }

}
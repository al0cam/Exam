package com.mendelu.fbe.gettingdone

import com.mendelu.fbe.termoverview.database.Ride
import com.mendelu.fbe.termoverview.database.IRideLocalRepository
import cz.mendelu.fbe.mytodo.architecture.BaseViewModel

class AddRideViewModel(private val repository: IRideLocalRepository) :BaseViewModel(){
    var id: Long? = null
    var ride: Ride

    init {
        ride = Ride("")
    }

    suspend fun insert(ride: Ride):Long {
        return repository.insert(ride)
    }
    suspend fun findById(id:Long):Ride {
        return repository.findById(id)
    }
}
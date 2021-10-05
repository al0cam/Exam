package com.mendelu.fbe.gettingdone

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mendelu.fbe.termoverview.database.Ride
import com.mendelu.fbe.termoverview.database.IRideLocalRepository


class ListViewModel(private val repository: IRideLocalRepository) :ViewModel() {
    fun getAll(): LiveData<MutableList<Ride>> {
        return repository.getAll()
    }

    suspend fun delete(id: Long) {
        repository.delete(repository.findById(id))
    }
}
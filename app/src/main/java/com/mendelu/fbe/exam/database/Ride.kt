package com.mendelu.fbe.termoverview.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "ride")
data class Ride(@ColumnInfo(name = "text") var text: String) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

    @ColumnInfo(name = "price")
    var price: Int? = null

    @ColumnInfo(name = "kilometers")
    var kilometers: Int? = null

    @ColumnInfo(name = "startOfRideLat")
    var startOfRideLat: Double?= null
    @ColumnInfo(name = "startOfRideLong")
    var startOfRideLong: Double?= null
    @ColumnInfo(name = "endOfRideLat")
    var endOfRideLat: Double?= null
    @ColumnInfo(name = "endOfRideLong")
    var endOfRideLong: Double?= null
}
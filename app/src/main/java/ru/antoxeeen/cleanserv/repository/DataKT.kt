package ru.antoxeeen.cleanserv.repository

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "DataKT")
data class DataKT(@SerializedName("address")
                  val address: String,
                  @SerializedName("date")
                  val date: String,
                  @SerializedName("route")
                  val route: Int,
                  @SerializedName("basketCount")
                  var basketCount: Int,
                  @SerializedName("basketVolume")
                  var basketVolume: Double,
                  @SerializedName("garbageVolume")
                  var garbageVolume: Double,
                  @SerializedName("garbageWeight")
                  var garbageWeight: Double,
                  @PrimaryKey(autoGenerate = true)
                  var id: Long) {
}
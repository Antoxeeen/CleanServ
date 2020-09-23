package ru.antoxeeen.cleanserv.repository

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DataKtDAO {
    @Insert
    fun insert(dataKT: DataKT)
    @Update
    fun update(dateKT: DataKT)
    @Delete
    fun delete(dateKT: DataKT)
    @Query("SELECT * FROM DataKT ORDER BY route ASC")
    fun getAllData(): LiveData<List<DataKT>>
    @Query("DELETE FROM DataKT")
    fun deleteAllData()


}
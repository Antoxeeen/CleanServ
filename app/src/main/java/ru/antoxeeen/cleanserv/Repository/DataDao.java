package ru.antoxeeen.cleanserv.Repository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
interface DataDao {

    @Insert
    void insert(Data data);

    @Update
    void update(Data data);

    @Delete
    void delete(Data data);

    @Query("SELECT * FROM Data ORDER BY route ASC")
    LiveData<List<Data>> getAllData();

    @Query("DELETE FROM Data")
    void deleteAllData();
}

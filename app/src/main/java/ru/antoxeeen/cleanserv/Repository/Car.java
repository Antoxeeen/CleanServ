package ru.antoxeeen.cleanserv.Repository;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
class Car {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    private String carName;

    private String carNumber;

    

}

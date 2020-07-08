package ru.antoxeeen.cleanserv.Repository;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
class Car {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "carName")
    private String carName;

    @ColumnInfo(name = "registrationNumber")
    private String registrationNumber;

    public Car(String carName, String registrationNumber) {
        this.carName = carName;
        this.registrationNumber = registrationNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCarName() {
        return carName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }
}

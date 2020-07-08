package ru.antoxeeen.cleanserv.Repository;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
class GarbageAddress {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "locality")
    private String locality;

    @ColumnInfo(name = "street")
    private String street;

    @ColumnInfo(name = "house_number")
    private String house_number;

    @Ignore
    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "carId")
    private int carId;

    public GarbageAddress(String locality, String street, String house_number, int carId) {
        this.locality = locality;
        this.street = street;
        this.house_number = house_number;
        this.carId = carId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getId() {
        return id;
    }

    public String getLocality() {
        return locality;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse_number() {
        return house_number;
    }

    public String getDescription() {
        return description;
    }

    public int getCarId() {
        return carId;
    }
}

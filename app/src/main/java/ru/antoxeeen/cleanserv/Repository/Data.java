package ru.antoxeeen.cleanserv.Repository;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Data")
public class Data {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "dbId")
    private int dbId;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "route")
    private int route;

    /*@ColumnInfo(name = "house_number")
    private String house_number;*/

    @ColumnInfo(name = "garbageVolume")
    private double garbageVolume;

    @ColumnInfo(name = "garbageWeight")
    private double garbageWeight;

    public Data(int dbId, String address, String date, int route, double garbageVolume, double garbageWeight) {
        this.dbId = dbId;
        this.address = address;
        this.date = date;
        this.route = route;
        this.garbageVolume = garbageVolume;
        this.garbageWeight = garbageWeight;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRoute(int route) {
        this.route = route;
    }

    public void setGarbageVolume(double garbageVolume) {
        this.garbageVolume = garbageVolume;
    }

    public void setGarbageWeight(double garbageWeight) {
        this.garbageWeight = garbageWeight;
    }

    public int getId() {
        return id;
    }

    public int getDbId() {
        return dbId;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public int getRoute() {
        return route;
    }

    public double getGarbageVolume() {
        return garbageVolume;
    }

    public double getGarbageWeight() {
        return garbageWeight;
    }
}

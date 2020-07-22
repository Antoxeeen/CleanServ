package ru.antoxeeen.cleanserv.Repository;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Data")
public class Data {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private long id;

    /*@ColumnInfo(name = "dbId")
    @SerializedName("id")
    private int dbId;*/

    @ColumnInfo(name = "address")
    @SerializedName("address")
    private String address;

    @ColumnInfo(name = "date")
    @SerializedName("date")
    private String date;

    @ColumnInfo(name = "route")
    @SerializedName("route")
    private int route;

    @ColumnInfo(name = "basketCount")
    @SerializedName("basketCount")
    private int basketCount;

    @ColumnInfo(name = "basketVolume")
    @SerializedName("basketVolume")
    private double basketVolume;

    @ColumnInfo(name = "garbageVolume")
    @SerializedName("garbageVolume")
    private double garbageVolume;

    @ColumnInfo(name = "garbageWeight")
    @SerializedName("garbageWeight")
    private double garbageWeight;

    public Data(long id, String address, String date, int route, int basketCount,
                double basketVolume, double garbageVolume, double garbageWeight) {
        this.id = id;
        this.address = address;
        this.date = date;
        this.route = route;
        this.basketCount = basketCount;
        this.basketVolume = basketVolume;
        this.garbageVolume = garbageVolume;
        this.garbageWeight = garbageWeight;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setBasketCount(int basketCount) {
        this.basketCount = basketCount;
    }

    public void setBasketVolume(double basketVolume) {
        this.basketVolume = basketVolume;
    }

    public void setGarbageVolume(double garbageVolume) {
        this.garbageVolume = garbageVolume;
    }

    public void setGarbageWeight(double garbageWeight) {
        this.garbageWeight = garbageWeight;
    }

    public long getId() {
        return id;
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

    public int getBasketCount() {
        return basketCount;
    }

    public double getBasketVolume() {
        return basketVolume;
    }

    public double getGarbageVolume() {
        return garbageVolume;
    }

    public double getGarbageWeight() {
        return garbageWeight;
    }
}

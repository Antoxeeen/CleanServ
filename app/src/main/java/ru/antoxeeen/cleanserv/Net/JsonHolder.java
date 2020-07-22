package ru.antoxeeen.cleanserv.Net;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.antoxeeen.cleanserv.Repository.Data;

public interface JsonHolder {

    @GET("persons")
    public Call<List<Data>> getAllData();

    @GET("/data")
    public Call<List<Data>> getAllDataAtDate(@Query("date") String date);
}

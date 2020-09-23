package ru.antoxeeen.cleanserv.net;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.antoxeeen.cleanserv.repository.DataKT;

public interface JsonHolder {

    @GET("persons")
    public Call<List<DataKT>> getAllData();

    @GET("data")
    public Call<List<DataKT>> getAllDataAtDate(@Query("date") String date);
}

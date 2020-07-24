package ru.antoxeeen.cleanserv.Repository;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.antoxeeen.cleanserv.Net.NetworkService;

public class DataRepository {

    private NetworkService networkService;
    private LiveData<List<Data>> data;
    private DataDao dataDao;

    public DataRepository(Context context) {
        LocalDB database = LocalDB.getInstance(context);
        dataDao = database.dataDao();
        data = dataDao.getAllData();
        networkService = NetworkService.getInstance();
    }

    public void getAllDataServerDB() {
        networkService.getJsonHolder()
                .getAllData()
                .enqueue(new Callback<List<Data>>() {
                    @Override
                    public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                        List<Data> currentDataList = response.body();
                        for (Data currentData : currentDataList) {
                            insertDataLocalDB(currentData);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Data>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    public void getAllDataServerDBAtDate(String date){
        networkService.getJsonHolder()
                .getAllDataAtDate(date)
                .enqueue(new Callback<List<Data>>() {
                    @Override
                    public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                        List<Data> currentDataList = response.body();
                        for (Data currentData : currentDataList) {
                            insertDataLocalDB(currentData);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Data>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    public LiveData<List<Data>> getAllDataLocalDB() {
        return data;
    }

    public void insertDataLocalDB(Data data) {
        new InsertDataLocalDBAsyncTask(dataDao).execute(data);
    }

    public void updateDataLocalDB(Data data) {
        new UpdateDataLocalDBAsyncTask(dataDao).execute(data);
    }

    public void deleteDataLocalDB(Data data) {
        new DeleteDataLocalDBAsyncTask(dataDao).execute(data);
    }

    public void deleteAllDataLocalDB() {
        new DeleteAllDataLocalDBAsyncTask(dataDao).execute();
    }

    private static class InsertDataLocalDBAsyncTask extends AsyncTask<Data, Void, Void> {

        private DataDao dataDao;

        public InsertDataLocalDBAsyncTask(DataDao dataDao) {
            this.dataDao = dataDao;
        }

        @Override
        protected Void doInBackground(Data... data) {
            dataDao.insert(data[0]);
            return null;
        }
    }

    private static class UpdateDataLocalDBAsyncTask extends AsyncTask<Data, Void, Void> {

        private DataDao dataDao;

        public UpdateDataLocalDBAsyncTask(DataDao dataDao) {
            this.dataDao = dataDao;
        }

        @Override
        protected Void doInBackground(Data... data) {
            dataDao.update(data[0]);
            return null;
        }
    }

    private static class DeleteDataLocalDBAsyncTask extends AsyncTask<Data, Void, Void> {

        private DataDao dataDao;

        public DeleteDataLocalDBAsyncTask(DataDao dataDao) {
            this.dataDao = dataDao;
        }

        @Override
        protected Void doInBackground(Data... data) {
            dataDao.delete(data[0]);
            return null;
        }
    }

    private static class DeleteAllDataLocalDBAsyncTask extends AsyncTask<Void, Void, Void> {

        private DataDao dataDao;

        public DeleteAllDataLocalDBAsyncTask(DataDao dataDao) {
            this.dataDao = dataDao;
        }

        @Override
        protected Void doInBackground(Void... Voids) {
            dataDao.deleteAllData();
            return null;
        }
    }

}

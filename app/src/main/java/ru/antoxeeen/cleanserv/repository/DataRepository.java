package ru.antoxeeen.cleanserv.repository;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.work.DataKt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.antoxeeen.cleanserv.net.NetworkService;

public class DataRepository {

    private NetworkService networkService;
    private LiveData<List<DataKT>> data;
    private DataKtDAO dataKTDao;

    public DataRepository(Context context) {
        LocalDBKT DBkt = LocalDBKT.Companion.getDatabaseInstance(context);
        dataKTDao = DBkt.dataKtDAO();
        data = dataKTDao.getAllData();
        networkService = NetworkService.getInstance();
    }

    public void getAllDataServerDB() {
        networkService.getJsonHolder()
                .getAllData()
                .enqueue(new Callback<List<DataKT>>() {
                    @Override
                    public void onResponse(Call<List<DataKT>> call, Response<List<DataKT>> response) {
                        List<DataKT> currentDataList = response.body();
                        for (DataKT currentData : currentDataList) {
                            insertDataLocalDB(currentData);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<DataKT>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    public void getAllDataServerDBAtDate(String date){
        networkService.getJsonHolder()
                .getAllDataAtDate(date)
                .enqueue(new Callback<List<DataKT>>() {
                    @Override
                    public void onResponse(Call<List<DataKT>> call, Response<List<DataKT>> response) {
                        List<DataKT> currentDataList = response.body();
                        for (DataKT currentData : currentDataList) {
                            insertDataLocalDB(currentData);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<DataKT>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    public LiveData<List<DataKT>> getAllDataLocalDB() {
        return data;
    }

    public void insertDataLocalDB(DataKT data) {
        new InsertDataLocalDBAsyncTask(dataKTDao).execute(data);
    }

    public void updateDataLocalDB(DataKT data) {
        new UpdateDataLocalDBAsyncTask(dataKTDao).execute(data);
    }

    public void deleteDataLocalDB(DataKT data) {
        new DeleteDataLocalDBAsyncTask(dataKTDao).execute(data);
    }

    public void deleteAllDataLocalDB() {
        new DeleteAllDataLocalDBAsyncTask(dataKTDao).execute();
    }

    private static class InsertDataLocalDBAsyncTask extends AsyncTask<DataKT, Void, Void> {

        private DataKtDAO dataKTDao;

        public InsertDataLocalDBAsyncTask(DataKtDAO dataKTDao) {
            this.dataKTDao = dataKTDao;
        }

        @Override
        protected Void doInBackground(DataKT... data) {
            dataKTDao.insert(data[0]);
            return null;
        }
    }

    private static class UpdateDataLocalDBAsyncTask extends AsyncTask<DataKT, Void, Void> {

        private DataKtDAO dataKTDao;

        public UpdateDataLocalDBAsyncTask(DataKtDAO dataKTDao) {
            this.dataKTDao = dataKTDao;
        }

        @Override
        protected Void doInBackground(DataKT... data) {
            dataKTDao.update(data[0]);
            return null;
        }
    }

    private static class DeleteDataLocalDBAsyncTask extends AsyncTask<DataKT, Void, Void> {

        private DataKtDAO dataKTDao;

        public DeleteDataLocalDBAsyncTask(DataKtDAO dataKTDao) {
            this.dataKTDao = dataKTDao;
        }

        @Override
        protected Void doInBackground(DataKT... dataKT) {
            dataKTDao.delete(dataKT[0]);
            return null;
        }
    }

    private static class DeleteAllDataLocalDBAsyncTask extends AsyncTask<Void, Void, Void> {

        private DataKtDAO dataKTDao;

        public DeleteAllDataLocalDBAsyncTask(DataKtDAO dataKTDao) {
            this.dataKTDao = dataKTDao;
        }

        @Override
        protected Void doInBackground(Void... Voids) {
            dataKTDao.deleteAllData();
            return null;
        }
    }

}

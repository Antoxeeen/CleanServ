package ru.antoxeeen.cleanserv.Repository;

import android.content.Context;
import android.os.AsyncTask;
import java.util.List;
import androidx.lifecycle.LiveData;

public class DataRepository {

    private LiveData<List<Data>> data;
    private DataDao dataDao;

    public DataRepository(Context context){
        LocalDB database = LocalDB.getInstance(context);
        dataDao = database.dataDao();
        data = dataDao.getAllData();
    }

    public LiveData<List<Data>> getAllData(){
        return data;
    }

    public void insertData(Data data){
        new InsertDataAsyncTask(dataDao).execute(data);
    }

    public void updateData(Data data){
        new UpdateDataAsyncTask(dataDao).execute(data);
    }

    public void deleteData(Data data){
        new DeleteDataAsyncTask(dataDao).execute(data);
    }

    public void deleteAllData(){
        new DeleteAllDataAsyncTask(dataDao).execute();
    }

    private static class InsertDataAsyncTask extends AsyncTask<Data, Void, Void>{

        private DataDao dataDao;

        public InsertDataAsyncTask(DataDao dataDao) {
            this.dataDao = dataDao;
        }

        @Override
        protected Void doInBackground(Data... data) {
            dataDao.insert(data[0]);
            return null;
        }
    }

    private static class UpdateDataAsyncTask extends AsyncTask<Data, Void, Void>{

        private DataDao dataDao;

        public UpdateDataAsyncTask(DataDao dataDao) {
            this.dataDao = dataDao;
        }

        @Override
        protected Void doInBackground(Data... data) {
            dataDao.update(data[0]);
            return null;
        }
    }

    private static class DeleteDataAsyncTask extends AsyncTask<Data, Void, Void>{

        private DataDao dataDao;

        public DeleteDataAsyncTask(DataDao dataDao) {
            this.dataDao = dataDao;
        }

        @Override
        protected Void doInBackground(Data... data) {
            dataDao.delete(data[0]);
            return null;
        }
    }

    private static class DeleteAllDataAsyncTask extends AsyncTask<Void, Void, Void>{

        private DataDao dataDao;

        public DeleteAllDataAsyncTask(DataDao dataDao) {
            this.dataDao = dataDao;
        }

        @Override
        protected Void doInBackground(Void... Voids) {
            dataDao.deleteAllData();
            return null;
        }
    }

}

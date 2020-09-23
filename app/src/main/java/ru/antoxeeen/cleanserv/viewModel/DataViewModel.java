package ru.antoxeeen.cleanserv.viewModel;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import ru.antoxeeen.cleanserv.repository.DataKT;
import ru.antoxeeen.cleanserv.repository.DataRepository;

public class DataViewModel extends AndroidViewModel {

    private DataRepository dataRepository;
    private LiveData<List<DataKT>> data;

    public DataViewModel(@NonNull Application application) {
        super(application);
        dataRepository = new DataRepository(application);
    }

    public void getAllDataServerDB(){
        dataRepository.getAllDataServerDB();
    }

    public void getDataServerDBAtDate(String date){
        dataRepository.getAllDataServerDBAtDate(date);
    }

    public LiveData<List<DataKT>> getAllDataFromLocalDB(){
        return dataRepository.getAllDataLocalDB();
    }

    public void insertData(DataKT data){
        dataRepository.insertDataLocalDB(data);
    }

    public void updateData(DataKT data){
        dataRepository.updateDataLocalDB(data);
    }

    public void deleteData(DataKT data){
        dataRepository.deleteDataLocalDB(data);
    }

    public void deleteAllData(){
        dataRepository.deleteAllDataLocalDB();
    }

}

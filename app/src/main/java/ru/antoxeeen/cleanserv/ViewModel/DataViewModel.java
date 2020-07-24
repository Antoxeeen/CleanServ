package ru.antoxeeen.cleanserv.ViewModel;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import ru.antoxeeen.cleanserv.Repository.Data;
import ru.antoxeeen.cleanserv.Repository.DataRepository;

public class DataViewModel extends AndroidViewModel {

    private DataRepository dataRepository;
    private LiveData<List<Data>> data;

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

    public LiveData<List<Data>> getAllDataFromLocalDB(){
        return dataRepository.getAllDataLocalDB();
    }

    public void insertData(Data data){
        dataRepository.insertDataLocalDB(data);
    }

    public void updateData(Data data){
        dataRepository.updateDataLocalDB(data);
    }

    public void deleteData(Data data){
        dataRepository.deleteDataLocalDB(data);
    }

    public void deleteAllData(){
        dataRepository.deleteAllDataLocalDB();
    }

}

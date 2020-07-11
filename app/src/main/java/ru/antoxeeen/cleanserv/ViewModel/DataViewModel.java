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

    public LiveData<List<Data>> getAllData(){
        return dataRepository.getAllData();
    }

    public void insertData(Data data){
        dataRepository.insertData(data);
    }

    public void updateData(Data data){
        dataRepository.updateData(data);
    }

    public void deleteData(Data data){
        dataRepository.deleteData(data);
    }

    public void deleteAllData(){
        dataRepository.deleteAllData();
    }

}

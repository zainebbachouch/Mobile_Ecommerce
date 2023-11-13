package com.codingstuff.shoeapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.codingstuff.shoeapp.repository.DataRepository;
import com.codingstuff.shoeapp.utils.model.State;
import com.codingstuff.shoeapp.utils.model.dataClass;

import java.util.List;

public class DataViewModel extends AndroidViewModel {
    private DataRepository dataRepository;
    private final LiveData<List<dataClass>> listLiveData;

    public DataViewModel(Application application) {
        super(application);
        dataRepository = new DataRepository(application);
        listLiveData = dataRepository.getAlldata();
    }

    public LiveData<List<dataClass>> getAlldatasFromVm() {
        return listLiveData;
    }

    public LiveData<List<dataClass>> getAlldatasFromVmByState(String state) {
        return dataRepository.getAlldataByState(state);
    }


    public void insertdata(dataClass data) {
        dataRepository.insertdata(data);
    }

    public void updateState(String e,int id){dataRepository.updateState(e,id);}

    public void deleteById(int id) {
        dataRepository.deleteById(id);
    }
}
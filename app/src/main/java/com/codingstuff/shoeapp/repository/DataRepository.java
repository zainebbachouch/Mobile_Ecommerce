package com.codingstuff.shoeapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.codingstuff.shoeapp.dao.DataDao;
import com.codingstuff.shoeapp.database.CartDatabase;
import com.codingstuff.shoeapp.utils.model.State;
import com.codingstuff.shoeapp.utils.model.dataClass;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DataRepository {
    private Executor executor = Executors.newSingleThreadExecutor();
    CartDatabase dataRoomDatabase;
    DataDao dataDao;
    private LiveData<List<dataClass>> listdata;
    private LiveData<List<dataClass>> listdataByState;


    public DataRepository(Application application) {
        dataRoomDatabase = CartDatabase.getInstance(application);
        dataDao = dataRoomDatabase.dataDao();
        listdata = dataDao.getdatas();
        listdataByState = dataDao.getdatasByState(State.IN_PROGRESS.name());
    }

    public void insertdata(dataClass data) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.insert(data);
            }
        });

    }

    public LiveData<List<dataClass>> getAlldata() {
        return listdata;
    }


    public LiveData<List<dataClass>> getAlldataByState(String state) {
        return listdataByState;
    }


    public void deleteById(int id) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.deleteAll(id);
            }
        });
    }

    public void updateState(String e, int id) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.UpdateState(e, id);
            }
        });


    }
}

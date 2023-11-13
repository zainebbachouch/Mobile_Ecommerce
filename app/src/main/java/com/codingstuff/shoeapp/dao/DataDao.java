package com.codingstuff.shoeapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.codingstuff.shoeapp.utils.model.State;
import com.codingstuff.shoeapp.utils.model.dataClass;

import java.util.List;

@Dao

public interface DataDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(dataClass data);

    @Query("SELECT * from data_db ORDER By nameArticle Asc")
    LiveData<List<dataClass>> getdatas();

    @Query("DELETE from data_db WHERE id = :d")
    void deleteAll(int d);

    @Query("UPDATE data_db SET state = :s WHERE id = :id")
    void UpdateState(String s,int id);


    @Query("SELECT * from data_db WHERE state LIKE :state")
    LiveData<List<dataClass>> getdatasByState(String state);
}

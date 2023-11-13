package com.codingstuff.shoeapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.codingstuff.shoeapp.dao.CartDAO;
import com.codingstuff.shoeapp.dao.DataDao;
import com.codingstuff.shoeapp.utils.model.ShoeCart;
import com.codingstuff.shoeapp.utils.model.dataClass;

@Database(entities = {ShoeCart.class,dataClass.class} , version = 2)
public abstract class CartDatabase extends RoomDatabase {

    public abstract CartDAO cartDAO();
    public abstract DataDao dataDao();
    private static CartDatabase instance;

    public static synchronized  CartDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext()
                            , CartDatabase.class , "ShoeDatabase")
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return instance;
    }
}

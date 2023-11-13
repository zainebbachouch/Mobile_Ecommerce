package com.codingstuff.shoeapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.codingstuff.shoeapp.R;
import com.codingstuff.shoeapp.utils.adapter.DeliveryAdapter;
import com.codingstuff.shoeapp.utils.model.dataClass;
import com.codingstuff.shoeapp.viewmodel.DataViewModel;

import java.util.ArrayList;
import java.util.List;

public class List_Cmd extends AppCompatActivity {
    public RecyclerView rv;
    public Button btn;
    private DataViewModel dataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cmd);
        rv = findViewById(R.id.rv);
        btn = findViewById(R.id.btn_action);
        dataViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(DataViewModel.class);
        dataViewModel.getAlldatasFromVm().observe(this, data ->
        {
            if (data != null && !data.isEmpty()) {
                DeliveryAdapter adapter = new DeliveryAdapter(data,getApplication());
                rv.setAdapter(adapter);
                rv.setLayoutManager(new LinearLayoutManager(this));

            }
        });

        btn.setOnClickListener(View ->{
            startActivity(new Intent(this , ListCommand.class));
        });
    }
}
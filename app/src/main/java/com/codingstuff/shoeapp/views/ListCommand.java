package com.codingstuff.shoeapp.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codingstuff.shoeapp.R;
import com.codingstuff.shoeapp.utils.adapter.ListCmdAdapter;
import com.codingstuff.shoeapp.utils.model.State;
import com.codingstuff.shoeapp.viewmodel.DataViewModel;

public class ListCommand extends AppCompatActivity {
    public RecyclerView rvlist;
    private DataViewModel dataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_command);
        rvlist = findViewById(R.id.list_cmd);
        rvlist.setLayoutManager(new LinearLayoutManager(this));
        dataViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(DataViewModel.class);
        dataViewModel.getAlldatasFromVmByState(State.IN_PROGRESS.name()).observe(this, data ->
        {
            if (data != null && !data.isEmpty()) {
                ListCmdAdapter adapter = new ListCmdAdapter(data,getApplication());
                rvlist.setAdapter(adapter);
                rvlist.setLayoutManager(new LinearLayoutManager(this));

            }
        });

    }
}
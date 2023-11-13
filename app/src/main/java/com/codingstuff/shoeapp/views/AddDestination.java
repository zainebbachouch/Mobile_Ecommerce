package com.codingstuff.shoeapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codingstuff.shoeapp.R;
import com.codingstuff.shoeapp.utils.model.State;
import com.codingstuff.shoeapp.utils.model.dataClass;
import com.codingstuff.shoeapp.viewmodel.DataViewModel;

public class AddDestination extends AppCompatActivity {
    private DataViewModel dataViewModel;
    private CardView cardView;
    private TextView tv;
    private EditText phone, destination;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_destination);
        phone = findViewById(R.id.idEdtUserName);
        destination = findViewById(R.id.idEdtPhone);
        cardView = findViewById(R.id.cartActivityCardView);
        tv = findViewById(R.id.idTVHeader);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(View -> {
            dataViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(DataViewModel.class);
            dataClass data = new dataClass("Articles", 54, destination.getText().toString(), "Boutique1", State.AVAILABLE, phone.getText().toString());
            dataViewModel.insertdata(data);
            phone.setVisibility(android.view.View.INVISIBLE);
            destination.setVisibility(android.view.View.INVISIBLE);
            btn.setVisibility(android.view.View.INVISIBLE);
            tv.setVisibility(android.view.View.INVISIBLE);
            cardView.setVisibility(android.view.View.VISIBLE);
        });


    }
}
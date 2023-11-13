package com.codingstuff.shoeapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codingstuff.shoeapp.R;
import com.codingstuff.shoeapp.utils.adapter.CartAdapter;
import com.codingstuff.shoeapp.utils.model.ShoeCart;
import com.codingstuff.shoeapp.viewmodel.CartViewModel;

import java.util.List;

public class CartActivity extends AppCompatActivity implements CartAdapter.CartClickedListeners {

    private RecyclerView recyclerView;
    private CartViewModel cartViewModel;
    private TextView totalCartPriceTv, textView;
    private AppCompatButton checkoutBtn;
    private Button btnAdd;
    private CartAdapter cartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("zainebbbbbbbbbbbbbbbb", "onCreate called");
        setContentView(R.layout.activity_cart);

        initializeVariables();


        cartViewModel.getAllCartItems().observe(this, new Observer<List<ShoeCart>>() {
            @Override
            public void onChanged(List<ShoeCart> shoeCarts) {
                double price = 0;
                cartAdapter.setShoeCartList(shoeCarts);
                for (int i=0;i<shoeCarts.size();i++){
                    price = price + shoeCarts.get(i).getTotalItemPrice();
                }
                totalCartPriceTv.setText(String.valueOf(price));
            }
        });

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartViewModel.deleteAllCartItems();
                textView.setVisibility(View.INVISIBLE);
                checkoutBtn.setVisibility(View.INVISIBLE);
                totalCartPriceTv.setVisibility(View.INVISIBLE);
                btnAdd.setVisibility(View.VISIBLE);
            }
        });
    }


    private void initializeVariables() {
        btnAdd = findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(View ->{
            Intent i = new Intent(getApplicationContext(), AddDestination.class);
            startActivity(i);
        });


        cartAdapter = new CartAdapter(this);
        Log.d("cwwwwwwwwwwwww", "Card clicked: " + R.id.cartIv);
        textView = findViewById(R.id.textView2);
        Log.d("cwwwwwwwwwwwww", "Card clicked: " + R.id.textView2);
        Log.d("cwwwwwwwwwwwww", "Card clicked: " + R.id.cartActivityCardView);
        totalCartPriceTv = findViewById(R.id.cartActivityTotalPriceTv);
        Log.d("cwwwwwwwwwwwww", "Card clicked: " + R.id.cartActivityTotalPriceTv);
        checkoutBtn = findViewById(R.id.cartActivityCheckoutBtn);
        Log.d("cwwwwwwwwwwwww", "Card clicked: " + R.id.cartActivityCheckoutBtn);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        recyclerView = findViewById(R.id.cartRecyclerView);
     //   Log.d("cwwwwwwwwwwwww",R.id.cartRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(cartAdapter);

    }

    @Override
    public void onDeleteClicked(ShoeCart shoeCart) {
        cartViewModel.deleteCartItem(shoeCart);
    }

    @Override
    public void onPlusClicked(ShoeCart shoeCart) {
        int quantity = shoeCart.getQuantity() + 1;
        cartViewModel.updateQuantity(shoeCart.getId() , quantity);
        cartViewModel.updatePrice(shoeCart.getId() , quantity*shoeCart.getShoePrice());
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMinusClicked(ShoeCart shoeCart) {
        int quantity = shoeCart.getQuantity() - 1;
        if (quantity != 0){
            cartViewModel.updateQuantity(shoeCart.getId() , quantity);
            cartViewModel.updatePrice(shoeCart.getId() , quantity*shoeCart.getShoePrice());
            cartAdapter.notifyDataSetChanged();
        }else{
            cartViewModel.deleteCartItem(shoeCart);
        }

    }
}
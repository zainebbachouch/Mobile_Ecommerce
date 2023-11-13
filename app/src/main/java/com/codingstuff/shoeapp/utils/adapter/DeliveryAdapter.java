package com.codingstuff.shoeapp.utils.adapter;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;


import com.codingstuff.shoeapp.R;
import com.codingstuff.shoeapp.utils.model.State;
import com.codingstuff.shoeapp.utils.model.dataClass;
import com.codingstuff.shoeapp.viewmodel.DataViewModel;

import java.util.List;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.MyViewHolder> {
    private List<dataClass> itemsList;
    private Application application;
    private DataViewModel dataViewModel;

    public DeliveryAdapter(List<dataClass> data, Application application) {
        this.itemsList = data;
        this.application = application;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final dataClass item = itemsList.get(position);
        holder.name.setText(item.getNameArticle());
        holder.from.setText(item.getFrom());
        holder.destination.setText(item.getDestination());
        holder.price.setText(String.valueOf(item.getPrice()));
        holder.status.setText(item.getState().name());
        holder.accept.setOnClickListener(view -> {
            dataViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(DataViewModel.class);
            dataViewModel.updateState(State.IN_PROGRESS.name(),item.getId());
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name, price, from, destination,status;
        public Button accept, deny;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            price = itemView.findViewById(R.id.tvPrice);
            from = itemView.findViewById(R.id.tvfrom);
            status = itemView.findViewById(R.id.tvstatus);
            destination = itemView.findViewById(R.id.tvdestination);
            accept = itemView.findViewById(R.id.btn_accept);
            deny = itemView.findViewById(R.id.btn_deny);
        }
    }
}

package com.codingstuff.shoeapp.utils.adapter;

import android.app.AlertDialog;
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
import com.codingstuff.shoeapp.utils.model.dataClass;
import com.codingstuff.shoeapp.viewmodel.DataViewModel;

import java.util.List;

public class ListCmdAdapter extends RecyclerView.Adapter<ListCmdAdapter.MyViewHolder> {
    private List<dataClass> itemsList;
    private DataViewModel dataViewModel;
    private Application application;


    public ListCmdAdapter(List<dataClass> data,Application application) {
        this.itemsList = data;
        this.application = application;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cmd_item, parent, false);
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
        holder.delivered.setOnClickListener(View -> {
            dataViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(DataViewModel.class);
            AlertDialog.Builder builder1 = new AlertDialog.Builder(View.getContext());
            builder1.setMessage("Are you sur you want to delete this item !");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    (dialog, id) -> dataViewModel.deleteById(item.getId()));

            builder1.setNegativeButton(
                    "No",
                    (dialog, id) -> dialog.cancel());

            AlertDialog alert11 = builder1.create();
            alert11.show();

        });

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name, price, from, destination, status;
        public Button onHold, delivered;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            price = itemView.findViewById(R.id.tvPrice);
            from = itemView.findViewById(R.id.tvfrom);
            destination = itemView.findViewById(R.id.tvdestination);
            status = itemView.findViewById(R.id.tvstatus);
            onHold = itemView.findViewById(R.id.btn_hold);
            delivered = itemView.findViewById(R.id.btn_delivered);
        }
    }

}



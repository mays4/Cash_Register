package com.example.cash_register;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryRecycleViewAdapter extends  RecyclerView.Adapter<HistoryRecycleViewAdapter.HistoryViewHolder>{
    ArrayList<History> historyArrayList;
    Context activityContext;

    interface toHistoryAdapter{
        void onHistoryclicked(int i);
    }
    toHistoryAdapter listener;
    public HistoryRecycleViewAdapter(ArrayList<History> list, Context context) {
        this.historyArrayList = list;
        activityContext = context;

    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {

         public HistoryViewHolder(@NonNull View itemView) {
             super(itemView);


         }
     }
    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(activityContext).inflate(R.layout.history_list_row,parent, false);
        return new HistoryViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {

        TextView name = holder.itemView.findViewById(R.id.history_row_name);
        TextView price =holder.itemView.findViewById(R.id.history_row_price);
        TextView quantity = holder.itemView.findViewById(R.id.history_row_quantity);
        History currentProduct = historyArrayList.get(position);
        name.setText(currentProduct.getName());
        price.setText(String.valueOf(currentProduct.getPrice()));
        quantity.setText(String.valueOf(currentProduct.getQuantity()));

        holder.itemView.setOnClickListener(v -> listener.onHistoryclicked(holder.getAdapterPosition()));



    }

    @Override
    public int getItemCount() {
        return historyArrayList.size();
    }




}

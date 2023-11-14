package com.example.cash_register;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryBaseAdAdapter extends BaseAdapter {
    ArrayList<History> historyArrayList;
    Context activityContext;

    HistoryBaseAdAdapter(ArrayList<History> list, Context context) {
        this.historyArrayList = list;
        activityContext = context;
    }

    @Override
    public int getCount() {
        return historyArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return historyArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder") View productRowView = LayoutInflater.from(activityContext).inflate(R.layout.history_list_row, viewGroup, false);
        TextView name = productRowView.findViewById(R.id.history_row_name);
        TextView price = productRowView.findViewById(R.id.history_row_price);
        TextView quantity = productRowView.findViewById(R.id.history_row_quantity);
        History currentProduct = historyArrayList.get(position);
        name.setText(currentProduct.getName());
        price.setText(String.valueOf(currentProduct.getPrice()));
        quantity.setText(String.valueOf(currentProduct.getQuantity()));

        return productRowView;
    }

}

package com.example.cash_register;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductsBaseAdapter extends BaseAdapter {
    ArrayList<Product> productArrayList;
    Context activityContext;
    ProductsBaseAdapter(ArrayList<Product> list, Context context) {
        this.productArrayList = list;
        activityContext = context;
    }

    @Override
    public int getCount() {
        return productArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return productArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder") View productRowView = LayoutInflater.from(activityContext).inflate(R.layout.cash_register_list_row, viewGroup, false);
        TextView name = productRowView.findViewById(R.id.product_name_row);
        TextView price = productRowView.findViewById(R.id.product_price_row);
        TextView quantity = productRowView.findViewById(R.id.product_quantity_row);
        Product currentProduct = productArrayList.get(position);
        name.setText(currentProduct.getName());
        price.setText(String.valueOf(currentProduct.getPrice()));
        quantity.setText(String.valueOf(currentProduct.getQuantity()));
        return productRowView;
    }
}


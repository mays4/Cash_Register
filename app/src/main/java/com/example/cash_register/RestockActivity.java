package com.example.cash_register;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RestockActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editText;
    Button ok_btn, cancel_btn;
    ArrayList<Product> itemsList;
    ListView reStockList;
    int selectedProductIndex =-1;
    String product_name;
    Double priceOfItem;
    Product selectedProduct;
    ProductsBaseAdapter productsBaseAdapter;

//    private static final String KEY_TEXT_INPUT = "text_input";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        if (savedInstanceState != null) {
//
//
//            editText.setText(savedInstanceState.getString("edit text", ""));
//            selectedProductIndex = savedInstanceState.getInt("selectedProductIndex", -1);
//
//
//        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);

        editText = findViewById(R.id.edit_text);
        ok_btn = findViewById(R.id.ok);
        cancel_btn = findViewById(R.id.cancel);
        ok_btn.setOnClickListener(this);
        cancel_btn.setOnClickListener(this);
        reStockList = findViewById(R.id.product_list_restock);
        itemsList = ((myApp) getApplication()).getProductItem();



        if (itemsList != null) {
            productsBaseAdapter = new ProductsBaseAdapter(itemsList, this);
            reStockList.setAdapter(productsBaseAdapter);
            reStockList.setOnItemClickListener((parent, view, position, id) -> {
                // Get the selected product based on the position
                selectedProduct = itemsList.get(position);

                // Now you can use the selectedProduct
                selectedProductIndex = position;
                priceOfItem = selectedProduct.getPrice();
                product_name = selectedProduct.getName();
            });
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ok) {
            if (editText.getText().toString().isEmpty() || selectedProductIndex < 0) {
                Toast.makeText(this, "Enter value", Toast.LENGTH_SHORT).show();
            } else {
                String inputText = editText.getText().toString();
                int restockQ = Integer.parseInt(inputText);
                selectedProduct.restockQuantity(itemsList, restockQ);
//                selectedProduct.setQuantity(restockQ);
                 if (itemsList != null ) {
                    productsBaseAdapter = new ProductsBaseAdapter(itemsList, this);
                    reStockList.setAdapter(productsBaseAdapter);

                } else {
                    // Handle the case where productList is null
                    Log.e("RestockActivity", "Product list is null");
                }

                editText.getText().clear();

                productsBaseAdapter.notifyDataSetChanged();

            }
        } else if (view.getId() == R.id.cancel) {
            editText.getText().clear();
            Intent toMangerIntent = new Intent(this, MangerActivity.class);
            startActivity(toMangerIntent);
            finish();

        }

    }

}


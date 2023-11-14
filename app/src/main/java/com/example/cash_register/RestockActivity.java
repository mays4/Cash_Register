package com.example.cash_register;

import androidx.appcompat.app.AppCompatActivity;


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
    int selectedProductIndex;
    String product_name;
    Double priceOfItem;
    Product selectedProduct;
    ProductsBaseAdapter productsBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);

        editText = findViewById(R.id.edit_text);
        ok_btn = findViewById(R.id.ok);
        cancel_btn = findViewById(R.id.cancel);
        ok_btn.setOnClickListener(this);
        cancel_btn.setOnClickListener(this);
        reStockList = findViewById(R.id.product_list_restock);
        itemsList = ((myApp) getApplication()).getProductItem();

//        productList = (ArrayList<Product>) getIntent().getSerializableExtra("itemList");

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
            if (editText.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter value", Toast.LENGTH_SHORT).show();
            } else {
                String inputText = editText.getText().toString();
                int restockQ = Integer.parseInt(inputText);
                selectedProduct.restockQuantity(itemsList, restockQ);
//                selectedProduct.setQuantity(restockQ);
                if (itemsList != null) {
                    productsBaseAdapter = new ProductsBaseAdapter(itemsList, this);
                    reStockList.setAdapter(productsBaseAdapter);

                } else {
                    // Handle the case where productList is null
                    Log.e("RestockActivity", "Product list is null");
                }


                editText.getText().clear();
//                if (selectedProduct != null && selectedProductIndex >= 0 ) {
                productsBaseAdapter.notifyDataSetChanged();
//                } else
//                {
//                    Toast.makeText(RestockActivity.this, "Select a product before restocking", Toast.LENGTH_SHORT).show();
//                }}
            }
        } else if (view.getId() == R.id.cancel) {
            editText.getText().clear();
        }

    }
}


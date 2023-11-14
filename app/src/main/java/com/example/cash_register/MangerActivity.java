package com.example.cash_register;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MangerActivity extends AppCompatActivity implements View.OnClickListener {
    Button history_btn, restock_btn;
    ArrayList<History> listOPurchaseHistory;
    ArrayList<Product> productList;

    // Define the ActivityResultLauncher


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manger);
        history_btn = findViewById(R.id.history);
        restock_btn = findViewById(R.id.restock);
        history_btn.setOnClickListener(this);
        restock_btn.setOnClickListener(this);
//        listOPurchaseHistory = (ArrayList<History>) getIntent().getSerializableExtra("historyList");
//        productList = (ArrayList<Product>) getIntent().getSerializableExtra("itemList");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.history) {
            Intent tohistoryIntent = new Intent(MangerActivity.this, HistoryListActivity.class);
            tohistoryIntent.putExtra("historyList", listOPurchaseHistory);
            startActivity(tohistoryIntent);
        } else if (view.getId() == R.id.restock) {
            Intent toRestockIntent = new Intent(MangerActivity.this, RestockActivity.class);
            toRestockIntent.putExtra("itemList", productList);

            startActivity(toRestockIntent);

        }

    }
}

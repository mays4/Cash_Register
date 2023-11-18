package com.example.cash_register;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;

public class HistoryListActivity extends AppCompatActivity implements HistoryRecycleViewAdapter.toHistoryAdapter{
    ArrayList<History> listOPurchaseHistory;
//   ListView historyList;
   RecyclerView historyViewList;

//   HistoryBaseAdAdapter historyBaseAdapter;
    HistoryRecycleViewAdapter historyRecycleViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
//        historyList = findViewById(R.id.history_list);
        historyViewList= findViewById(R.id.history_list);

        listOPurchaseHistory = ((myApp) getApplication()).getListOfProductsPurchased();
//        historyBaseAdapter = new HistoryBaseAdAdapter(listOPurchaseHistory, this);
//        historyList.setAdapter(historyBaseAdapter);
        historyRecycleViewAdapter = new HistoryRecycleViewAdapter(listOPurchaseHistory,this);
        historyViewList.setAdapter(historyRecycleViewAdapter);
        historyRecycleViewAdapter.listener = this;
        historyViewList.setLayoutManager(new LinearLayoutManager(this));

//        Intent toDetailsIntent = new Intent(HistoryListActivity.this, HistoryDetailsActivity.class);


//        historyList.setOnItemClickListener((parent, view, position, id) -> {
//            // Create an Intent to start HistoryDetailsActivity
//            Intent toDetailsIntent = new Intent(HistoryListActivity.this, HistoryDetailsActivity.class);
//
//            // Put the details of the selected item in the Intent as an extra
//            History selectedProduct = listOPurchaseHistory.get(position);
//            toDetailsIntent.putExtra("details", selectedProduct);
//            startActivity(toDetailsIntent);
//
//        });


    }

    @Override
    public void onHistoryclicked(int i ) {
        Intent toDetailsIntent = new Intent(HistoryListActivity.this, HistoryDetailsActivity.class);

        // Put the details of the selected item in the Intent as an extra
        History selectedProduct = listOPurchaseHistory.get(i);
        toDetailsIntent.putExtra("details", selectedProduct);
        startActivity(toDetailsIntent);

    }
}
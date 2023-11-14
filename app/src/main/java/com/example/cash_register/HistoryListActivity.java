package com.example.cash_register;



import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class HistoryListActivity extends AppCompatActivity {
    ArrayList<History> listOPurchaseHistory;
   ListView historyList;
//    RecyclerView historyRecycleView;
   HistoryBaseAdAdapter historyBaseAdapter;
//    HistoryRecycleViewAdapter historyRecycleViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
        historyList = findViewById(R.id.history_list);
//        listOPurchaseHistory= (ArrayList<History>) getIntent().getSerializableExtra("historyList");
//        listOPurchaseHistory = ((myApp).
        listOPurchaseHistory = ((myApp) getApplication()).getListOfProductsPurchased();
//        historyBaseAdapter = new HistoryBaseAdAdapter(listOPurchaseHistory, this);
//        historyList.setAdapter(historyBaseAdapter);
        historyBaseAdapter = new HistoryBaseAdAdapter(listOPurchaseHistory,this);
        historyList.setAdapter(historyBaseAdapter);
//        historyList.setLayoutManager(new LinearLayoutManager(this));
//        Intent toDetailsIntent = new Intent(HistoryListActivity.this, HistoryDetailsActivity.class);


        historyList.setOnItemClickListener((parent, view, position, id) -> {
            // Create an Intent to start HistoryDetailsActivity
            Intent toDetailsIntent = new Intent(HistoryListActivity.this, HistoryDetailsActivity.class);

            // Put the details of the selected item in the Intent as an extra
            History selectedProduct = listOPurchaseHistory.get(position);
            toDetailsIntent.putExtra("details", selectedProduct);
            startActivity(toDetailsIntent);

        });


    }

    }
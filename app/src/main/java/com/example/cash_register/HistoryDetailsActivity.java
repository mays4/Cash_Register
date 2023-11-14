package com.example.cash_register;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HistoryDetailsActivity extends AppCompatActivity {
    TextView orderReport;
    History details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);

        orderReport = findViewById(R.id.history_details);

        details = (History) getIntent().getSerializableExtra("details");

        if (details != null) {
            String msg = "Product:" + details.getName() + "\n" +
                    "Price: " + details.getQuantity() + "\n" +
                    "Purchase Date: " + details.getProduct_order_date();

            orderReport.setText(msg);
        }
    }
}

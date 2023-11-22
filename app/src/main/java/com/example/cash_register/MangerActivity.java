package com.example.cash_register;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MangerActivity extends AppCompatActivity implements View.OnClickListener {
    Button history_btn, restock_btn;

    SwitchCompat backgroundSwitch;
    int background_colourId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            background_colourId = savedInstanceState.getInt("backgroundColor");
            View v = this.getWindow().getDecorView();
            v.setBackgroundColor(getResources().getColor(background_colourId, null));
        }
        setContentView(R.layout.activity_manger);
        history_btn = findViewById(R.id.history);
        restock_btn = findViewById(R.id.restock);
        history_btn.setOnClickListener(this);
        restock_btn.setOnClickListener(this);

        backgroundSwitch = findViewById(R.id.dark_light_switch);
        backgroundSwitch.setOnClickListener(view -> {
            View v =   MangerActivity.this.getWindow().getDecorView();

            if ( backgroundSwitch.isChecked()) {
                v.setBackgroundColor(getResources().getColor(R.color.DarkGray, null));
                background_colourId = R.color.DarkGray;
            }else {
                v.setBackgroundColor(getResources().getColor(R.color.white, null));
                background_colourId = R.color.white;
            }
        });

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.history) {
            Intent tohistoryIntent = new Intent(MangerActivity.this, HistoryListActivity.class);

            startActivity(tohistoryIntent);
        } else if (view.getId() == R.id.restock) {
            Intent toRestockIntent = new Intent(MangerActivity.this, RestockActivity.class);


            startActivity(toRestockIntent);

        }

    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("backgroundColor", background_colourId);
    }
}


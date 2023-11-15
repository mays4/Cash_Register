package com.example.cash_register;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView displayQuantity, displayTotal,text_input;
    Button one_button, two_btn, three_btn, four_btn, five_btn, six_btn,
            seven_btn, eight_btn, nine_btn, zero_btn, result, clear_btn,manger_btn;


    ArrayList<Product> itemsList;
    ArrayList<History> listOPurchaseHistory;
    int selectedProductIndex = -1;
    ListView productsList;
    Double priceOfItem;
    String product_name ;
    ProductsBaseAdapter productsBaseAdapter;
//    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_TEXT_INPUT = "text_input";
    private static final String KEY_TOTAL = "total";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        if (savedInstanceState != null) {
//            displayQuantity.setText(savedInstanceState.getString("quantity", ""));
//            text_input.setText(savedInstanceState.getString(KEY_TEXT_INPUT, ""));
//            displayTotal.setText(savedInstanceState.getString(KEY_TOTAL, ""));
//            selectedProductIndex = savedInstanceState.getInt("selectedProductIndex", -1);
//
//
//        }
        setContentView(R.layout.activity_main);
        displayQuantity = findViewById(R.id.quantity);
        displayTotal = findViewById(R.id.total);
        one_button = findViewById(R.id.one);
        two_btn = findViewById(R.id.two);
        three_btn = findViewById(R.id.three);
        four_btn = findViewById(R.id.four);
        five_btn = findViewById(R.id.five);
        six_btn = findViewById(R.id.six);
        seven_btn = findViewById(R.id.seven);
        eight_btn = findViewById(R.id.eight);
        nine_btn = findViewById(R.id.nine);
        zero_btn = findViewById(R.id.zero);
        result = findViewById(R.id.buy);
        clear_btn = findViewById(R.id.clear);
        manger_btn = findViewById(R.id.manger);
        productsList = findViewById(R.id.product_list);
        text_input = findViewById(R.id.textView);
        result.setOnClickListener(this);
        one_button.setOnClickListener(this);
        two_btn.setOnClickListener(this);
        three_btn.setOnClickListener(this);
        four_btn.setOnClickListener(this);
        five_btn.setOnClickListener(this);
        six_btn.setOnClickListener(this);
        seven_btn.setOnClickListener(this);
        eight_btn.setOnClickListener(this);
        nine_btn.setOnClickListener(this);
        zero_btn.setOnClickListener(this);
        clear_btn.setOnClickListener(this);
        manger_btn.setOnClickListener(this);
        listOPurchaseHistory = ((myApp) getApplication()).getListOfProductsPurchased();
        itemsList = ((myApp) getApplication()).getProductItem();


        productsBaseAdapter = new ProductsBaseAdapter(itemsList, this);
        productsList.setAdapter(productsBaseAdapter);
//        productsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          productsList.setOnItemClickListener((parent, view, position, id) -> {
              selectedProductIndex = position;
              if (selectedProductIndex >= 0) {
                  Product selectedProduct = itemsList.get(selectedProductIndex);
                  priceOfItem = selectedProduct.getPrice();
                  product_name = selectedProduct.getName();
                  text_input.setText(product_name);
                  displayTotal.setText(String.valueOf(priceOfItem));
              }
          });

    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.clear) {
            displayQuantity.setText("");
            text_input.setText("");

        } else {

            if (view.getId() == R.id.one || view.getId() == R.id.two
                    || view.getId() == R.id.three || view.getId() == R.id.four ||
                    view.getId() == R.id.five || view.getId() == R.id.six || view.getId() == R.id.seven
                    || view.getId() == R.id.eight || view.getId() == R.id.nine || view.getId() == R.id.zero

            ) {
                String value = ((Button) view).getText().toString();
                displayQuantity.setText(displayQuantity.getText() + value);


            } else if (view.getId() == R.id.buy) {
                DateTimeFormatter dateOfOrder = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String quantityText = displayQuantity.getText().toString();
                if(selectedProductIndex < 0){
                    Toast.makeText(this, "Select an item", Toast.LENGTH_SHORT).show();
                }else if (quantityText.isEmpty() || text_input.getText().toString().equals("")) {
                    Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    int quantityValue = Integer.parseInt(quantityText);
                    String formattedDate = dateOfOrder.format(now);
                    Product newProduct = new Product(product_name, priceOfItem, quantityValue);
                    History newHistory = new History(product_name, priceOfItem, quantityValue, formattedDate);
                    double result = newProduct.total(itemsList,quantityValue, priceOfItem);
                    if (result == 0.001) {
                        Toast.makeText(this, "No enough quantity in stock ", Toast.LENGTH_SHORT).show();
                    } else if (result == 0.002) {
                        Toast.makeText(
                                this, "Enter a valid quantity ", Toast.LENGTH_SHORT).show();
                    } else {
                        @SuppressLint("DefaultLocale") String resultAsString = String.format("%.2f", result);
                        displayTotal.setText(resultAsString);

                        String mesT = "Your purchase is " + quantityValue + " " + product_name + " for " + resultAsString;

                        // Add the new product to the List of history
                        listOPurchaseHistory.add(newHistory);
                        // Update the quantity in the product list
                        newProduct.updateQuantity(itemsList, quantityValue);

                          productsBaseAdapter.notifyDataSetChanged();

                           text_input.setText("");
                           displayQuantity.setText("");

                          showAlertDialog(mesT);

                    }
                }
            } else if (view.getId() == R.id.manger) {
                Intent toMangerIntent = new Intent(this, MangerActivity.class);

                startActivity(toMangerIntent);

            }

        }

    }
    public  void showAlertDialog(String mes){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thank you for your Purchase").setMessage(mes);
        AlertDialog alertDialog  = builder.create();
        alertDialog.show();


    }

}
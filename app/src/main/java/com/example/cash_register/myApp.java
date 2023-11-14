package com.example.cash_register;

import android.app.Application;

import java.util.ArrayList;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;

public class myApp extends Application {
    private ArrayList<History> productPurchased;
    private ArrayList<Product> productItem;


    private void initializeProductItem() {
        // Initialize the product items if not already initialized
        if (productItem == null) {
            productItem = new ArrayList<>(0);
            productItem.add(new Product("Pants", 20.44, 10));
            productItem.add(new Product("Shoes", 10.44, 100));
            productItem.add(new Product("Hats", 59, 30));
        }
    }

    public ArrayList<History> getListOfProductsPurchased() {
        if (productPurchased == null) {
            productPurchased = new ArrayList<>(0);
        }
        return productPurchased;
    }

    public ArrayList<Product> getProductItem() {
        initializeProductItem();  // Ensure productItem is initialized
        return productItem;
    }


}

package com.example.cash_register;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {
    String name;
    double price;
    int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double total(ArrayList<Product> ListOfProducts, int quantityOrdered,double price) {
        for (Product product : ListOfProducts) {
            if (product.getName().equals(this.getName())) {

                if (quantityOrdered > product.getQuantity()) {
                    Log.d("getTotal", String.valueOf(quantity));
                    return 0.001;
                } else if (quantityOrdered == 0) {

                    return 0.002;
                } else {
                    Log.d("total", String.valueOf(quantityOrdered));

                }

            }
        }
        return quantityOrdered * price;

    }
    public void updateQuantity(ArrayList<Product> ListOfProducts, int quantityOrdered) {
        for (Product product : ListOfProducts) {
            if (product.getName().equals(this.getName())) {
                product.setQuantity(product.getQuantity() - quantityOrdered);

                Log.d("updated quantity", String.valueOf(product.getQuantity()));
                break;
            }
        }
    }

    public void restockQuantity(ArrayList<Product> productList, int restockQ) {
        for (Product product : productList) {
            if (product.getName().equals(this.getName())) {
                product.setQuantity(product.getQuantity() + restockQ);

                Log.d("updated stock", String.valueOf(product.getQuantity()));
                break;
            }
        }
    }
}





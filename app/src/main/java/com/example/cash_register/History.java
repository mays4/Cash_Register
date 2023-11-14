package com.example.cash_register;


import java.io.Serializable;

public class History implements Serializable{
        String name;
        double price;
        int quantity;
        String product_order_date;


        public History(String name, double price, int quantity,String product_order_date) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.product_order_date =product_order_date;


        }

        public String getProduct_order_date() {
            return product_order_date;
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

}
package Controller;

import Modul.User;

import java.util.Map;
import java.util.ArrayList;

import Modul.Dummy;
import Modul.Product;
import Modul.Transaction;
import Modul.Seller;

import javax.swing.*;


public class ViewIncome {
    public double viewIncome(JTextField shopName) {
        double income = 0;
        for (Transaction transaction : Dummy.listTransaction.values()) {
            if (transaction.getShopName().equals(shopName.getText())) {
                for (Product listProduct : transaction.getListProduct()) { 
                    income += listProduct.getPrice(); 
                }
            }
        }
        return income;
    }
}

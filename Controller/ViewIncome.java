package Controller;

import Modul.User;

import java.util.Map;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modul.Dummy;
import Modul.Product;
import Modul.Transaction;
import Modul.Seller;

import javax.swing.*;

public class ViewIncome {
    public static int viewIncome(String sellerName) {
        String query = "SELECT t.*, p.*, tok.*"
                +
                "FROM transaction t " +
                "LEFT JOIN toko tok ON t.id_shop = tok.id_shop " +
                "LEFT JOIN product p ON p.seller_name = tok.username " +
                "WHERE tok.username = ? " +
                "ORDER BY t.date DESC";

        int income = 0; 

        try {
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            ResultSet rs = null;
            st = DatabaseHandler.connect().prepareStatement(query);
            st.setString(1, sellerName);
            rs = st.executeQuery();

            while (rs.next()) {
                int price = rs.getInt("p.price"); 
                int discount = rs.getInt("p.discount");
                int totalPrice = price - (price * (discount/100));
                income += totalPrice;
            }

            if (income == 0) {
                return 1;
            }

            return income; 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

}

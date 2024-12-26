package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

public class BalanceSection {
    public static double viewBalance(String username) {
        double balance = 0.0;

        try {
            String query = "SELECT emoney FROM user WHERE username = ?";
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            if (rs != null) {
                balance = rs.getDouble("emoney");
            }

            rs.close();
            st.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        return balance;
    }

    public static boolean addBalance(String username, double balance) {
        try {
            double balances = viewBalance(username);
            balance += balances;
            String query = "UPDATE user SET emoney = ? WHERE username = ?";
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            st.setDouble(1, balance);
            st.setString(2, username);

            int rowsAffected = st.executeUpdate();

            st.close();
            st.close();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding emoney: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }
}

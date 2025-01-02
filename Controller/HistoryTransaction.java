package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Modul.*;

import javax.swing.*;

public class HistoryTransaction {

    public static ArrayList<Transaction> transactionHistory(String username) {
        ArrayList<Transaction> transactionList = new ArrayList<>();
        String query = 
            "SELECT t.*, dt.quantity, p.*, tok.*, b.author, b.synopsis, b.title, b.release_date, b.page_num, g.exp_date, g.production_date, c.color AS clothing_color, c.size, e.color AS electronic_color, e.manual_book, e.warranty " +
            "FROM transaction t " +
            "INNER JOIN detail_transaction dt ON t.id_transaksi = dt.id_transaksi " +
            "INNER JOIN product p ON dt.id_product = p.id_product " +
            "LEFT JOIN book b ON p.id_product = b.id_product " +
            "LEFT JOIN grocery g ON p.id_product = g.id_product " +
            "LEFT JOIN clothing c ON p.id_product = c.id_product " +
            "LEFT JOIN electronic e ON p.id_product = e.id_product " +
            "LEFT JOIN toko tok ON t.id_shop = tok.id_shop " +
            "WHERE t.username = ? " +
            "ORDER BY t.id_transaksi";
    
        try {
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
    
            Transaction currentTransaction = null;
            ArrayList<Product> productList = null;
            String currentTransactionId = null;
    
            while (rs.next()) {
                String transactionId = rs.getString("id_transaksi");
    
                if (!transactionId.equals(currentTransactionId)) {
                    if (currentTransaction != null) {
                        transactionList.add(currentTransaction);
                    }
    
                    productList = new ArrayList<>();
                    currentTransaction = new Transaction(
                        productList,
                        rs.getString("shop_name"),
                        rs.getString("username"),
                        ShipmentStatus_Enum.COMPLETED,
                        rs.getDate("date")
                    );
    
                    currentTransactionId = transactionId;
                }
    
                Product product = null;
                if (rs.getString("title") != null && !rs.getString("title").isEmpty()) {
                    product = new Book(
                        rs.getString("author"),
                        rs.getInt("page_num"),
                        rs.getDate("release_date"),
                        rs.getString("synopsis"),
                        rs.getString("title"),
                        Integer.toString(rs.getInt("id_product")),
                        rs.getString("seller_name"),
                        rs.getString("name"),
                        rs.getInt("stock"),
                        rs.getDouble("discount"),
                        rs.getDouble("price"),
                        rs.getString("photo_product_path")
                    );
                } else if (rs.getString("size") != null && !rs.getString("size").isEmpty()) {
                    product = new Clothing(
                        rs.getString("clothing_color"),
                        ClothingSize_Enum.valueOf(rs.getString("size").toUpperCase()),
                        Integer.toString(rs.getInt("id_product")),
                        rs.getString("seller_name"),
                        rs.getString("name"),
                        rs.getInt("stock"),
                        rs.getDouble("discount"),
                        rs.getDouble("price"),
                        rs.getString("photo_product_path")
                    );
                } else if (rs.getString("warranty") != null && !rs.getString("warranty").isEmpty()) {
                    product = new Electronic(
                        rs.getString("electronic_color"),
                        rs.getString("manual_book"),
                        rs.getDate("warranty"),
                        Integer.toString(rs.getInt("id_product")),
                        rs.getString("seller_name"),
                        rs.getString("name"),
                        rs.getInt("stock"),
                        rs.getDouble("discount"),
                        rs.getDouble("price"),
                        rs.getString("photo_product_path")
                    );
                } else {
                    product = new Grocery(
                        rs.getDate("exp_date"),
                        rs.getDate("production_date"),
                        Integer.toString(rs.getInt("id_product")),
                        rs.getString("seller_name"),
                        rs.getString("name"),
                        rs.getInt("stock"),
                        rs.getDouble("discount"),
                        rs.getDouble("price"),
                        rs.getString("photo_product_path")
                    );
                }
    
                if (product != null) {
                    productList.add(product);
                }
            }
    
            if (currentTransaction != null) {
                transactionList.add(currentTransaction);
            }
    
            rs.close();
            st.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    
        return transactionList;
    }
    

    public static void transactionSeller(String username) {

    }
}

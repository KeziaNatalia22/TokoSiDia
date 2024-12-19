package Controller;

import Modul.Book;
import Modul.Clothing;
import Modul.ClothingSize_Enum;
import Modul.Electronic;
import Modul.Grocery;
import Modul.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class BuyerSection {

    public static ArrayList<Product> searchProduct(String input) {
        ArrayList<Product> searchedProduct = new ArrayList<Product>();
        String query =  "SELECT *" 
                        + "FROM product AS p "
                        + "INNER JOIN clothing AS c ON c.id_product = p.id_product "
                        + "INNER JOIN grocery AS g ON g.id_product = p.id_product "
                        + "INNER JOIN book AS b ON b.id_product = p.id_product "
                        + "INNER JOIN electronic AS e ON e.id_product = p.id_product "
                        + "WHERE p.name LIKE ?";

        try {
            PreparedStatement st = DatabaseHandler.con.prepareStatement(query);

            st.setString(1, '%'+input+'%');

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    if (!rs.getString("title").isEmpty()) {
                        Book book = new Book(
                            rs.getString("name"), 
                            Integer.toString(rs.getInt("id_product")), 
                            rs.getDouble("price"), 
                            rs.getDouble("discount"), 
                            rs.getInt("stock"), 
                            rs.getString("title"), 
                            rs.getString("author"),
                            rs.getString("synopsis"),
                            rs.getDate("release_date"), 
                            rs.getInt("page_num"));
                        searchedProduct.add(book);
                    }
                    else if (!rs.getString("size").isEmpty()) {
                        Clothing cloth = new Clothing(
                            rs.getString("name"), 
                            Integer.toString(rs.getInt("id_product")), 
                            rs.getDouble("price"), 
                            rs.getDouble("discount"), 
                            rs.getInt("stock"), 
                            ClothingSize_Enum.valueOf(rs.getString("size").toUpperCase()),
                            rs.getString("color"));
                        searchedProduct.add(cloth);
                    }
                    else if (!rs.getString("warranty").isEmpty()) {
                        Electronic electronic = new Electronic(
                            rs.getString("name"), 
                            Integer.toString(rs.getInt("id_product")), 
                            rs.getDouble("price"), 
                            rs.getDouble("discount"), 
                            rs.getInt("stock"), 
                            rs.getDate("warranty"), 
                            rs.getString("manual_book"), 
                            rs.getString("color"));
                        searchedProduct.add(electronic);
                    }
                    else{
                        Grocery grocery = new Grocery(
                            rs.getString("name"), 
                            Integer.toString(rs.getInt("id_product")), 
                            rs.getDouble("price"), 
                            rs.getDouble("discount"), 
                            rs.getInt("stock"), 
                            rs.getDate("exp_date"), 
                            rs.getDate("production_date"));
                        searchedProduct.add(grocery);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Maaf sekali barang yang anda cari saat ini tidak tersedia", "Hasil Pencarian Barang", JOptionPane.INFORMATION_MESSAGE);
            }
            return searchedProduct;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Checking Unique Username", "Error", JOptionPane.ERROR_MESSAGE);
            return searchedProduct;
        }
    }
}

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

    public static ArrayList<Product> searchProduct(String input, int offset) {
        ArrayList<Product> searchedProduct = new ArrayList<Product>();
        String query =  "SELECT p.*, c.*, g.*, b.*, e.* "
                        + "FROM product AS p "
                        + "LEFT JOIN clothing AS c ON c.id_product = p.id_product "
                        + "LEFT JOIN grocery AS g ON g.id_product = p.id_product "
                        + "LEFT JOIN book AS b ON b.id_product = p.id_product "
                        + "LEFT JOIN electronic AS e ON e.id_product = p.id_product "
                        + "WHERE p.name LIKE ?"
                        + "LIMIT 8 OFFSET ?";

        try {
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            st.setString(1, "%" + input + "%");
            st.setInt(2, offset);
            ResultSet rs = st.executeQuery();
            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                if (rs.getString("title") != null && !rs.getString("title").isEmpty()) {
                    Book book = new Book( 
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
                        rs.getString("photo_product_path"));
                    searchedProduct.add(book);
                } 
                else if (rs.getString("size") != null && !rs.getString("size").isEmpty()) {
                    Clothing cloth = new Clothing( 
                        rs.getString("color"),
                        ClothingSize_Enum.valueOf(rs.getString("size").toUpperCase()),
                        Integer.toString(rs.getInt("id_product")), 
                        rs.getString("seller_name"), 
                        rs.getString("name"), 
                        rs.getInt("stock"), 
                        rs.getDouble("discount"), 
                        rs.getDouble("price"), 
                        rs.getString("photo_product_path"));
                    searchedProduct.add(cloth);
                } 
                else if (rs.getString("warranty") != null && !rs.getString("warranty").isEmpty()) {
                    Electronic electronic = new Electronic( 
                        rs.getString("color"),
                        rs.getString("manual_book"), 
                        rs.getDate("warranty"), 
                        Integer.toString(rs.getInt("id_product")), 
                        rs.getString("seller_name"), 
                        rs.getString("name"), 
                        rs.getInt("stock"), 
                        rs.getDouble("discount"), 
                        rs.getDouble("price"), 
                        rs.getString("photo_product_path"));
                    searchedProduct.add(electronic);
                } 
                else {
                    Grocery grocery = new Grocery( 
                        rs.getDate("exp_date"), 
                        rs.getDate("production_date"),
                        Integer.toString(rs.getInt("id_product")), 
                        rs.getString("seller_name"), 
                        rs.getString("name"), 
                        rs.getInt("stock"), 
                        rs.getDouble("discount"), 
                        rs.getDouble("price"), 
                        rs.getString("photo_product_path"));
                    searchedProduct.add(grocery);
                }
            }

            if (!hasResults && offset == 0) {
                JOptionPane.showMessageDialog(null, "Maaf sekali barang yang anda cari saat ini tidak tersedia", 
                    "Hasil Pencarian Barang", JOptionPane.INFORMATION_MESSAGE);
            }
            else if (!hasResults) {
                JOptionPane.showMessageDialog(null, "Sudah tidak ada barang", 
                    "Hasil Pencarian Barang", JOptionPane.INFORMATION_MESSAGE);
            }
            return searchedProduct;

        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getLocalizedMessage());
            // Handle or log the error, return empty list in case of failure
            return searchedProduct;
        }

    }
}

package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.xml.crypto.Data;

import Modul.Book;
import Modul.Clothing;
import Modul.ClothingSize_Enum;
import Modul.Dummy;
import Modul.Electronic;
import Modul.Grocery;
import Modul.Product;
import Modul.Seller;
import Modul.User;


public class RemoveProduct {
    public static void removeProduct (String id){
        String query = "DELETE FROM product WHERE id_product = ?";
        try {
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            st.setInt(1, Integer.parseInt(id));
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DatabaseHandler.disconnect();
    }

    public static ArrayList<Product> productList(String sellerName) {
     ArrayList<Product> searchedProduct = new ArrayList<Product>();
        String query =  "SELECT p.*, c.*, g.*, b.*, e.* "
                        + "FROM product AS p "
                        + "LEFT JOIN clothing AS c ON c.id_product = p.id_product "
                        + "LEFT JOIN grocery AS g ON g.id_product = p.id_product "
                        + "LEFT JOIN book AS b ON b.id_product = p.id_product "
                        + "LEFT JOIN electronic AS e ON e.id_product = p.id_product "
                        + "WHERE p.seller_name LIKE ?";

        try {
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            st.setString(1, sellerName);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
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
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getLocalizedMessage());
            // Handle or log the error, return empty list in case of failure
        }
        DatabaseHandler.disconnect();
        return searchedProduct;

    }
}

package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import Modul.*;

public class ProductSection{
    public static void addProduct(Product product){
        String query = "insert into product values(?,?,?,?,?,?,?)";
        int key = 0;
        try {
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            st.setInt(1,0);
            st.setString(2, product.getSellerName());
            st.setString(3, product.getName());
            st.setInt(4, product.getStock());
            st.setDouble(5, product.getDiscount());
            st.setDouble(6, product.getPrice());
            st.setString(7, RelativePath.getRelativePath(product.getPhotoProduct()));
            st.executeUpdate();
            
            ResultSet keys = st.getGeneratedKeys();    
            keys.next();
            key = keys.getInt(1);
            DatabaseHandler.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(product instanceof Book book){
            try {
                query = "insert into book values (?,?,?,?,?,?)";
                PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
                //Book book = (Book) product;
                st.setInt(1,key);
                st.setString(2, book.getTitle());
                st.setString(3, book.getAuthor());
                st.setString(4, book.getSynopsis());
                st.setDate(5, java.sql.Date.valueOf(book.getReleaseDate().toString()));
                st.setInt(6, book.getPageNum());
                st.executeUpdate();
                DatabaseHandler.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else if(product instanceof Clothing clothing){
            try {
                query = "insert into clothing values (?,?,?)";
                PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
                //Clothing clothing = (Clothing) product;
                st.setInt(1,key);
                st.setString(2, clothing.getSize().toString());
                st.setString(3, clothing.getColor());
                st.executeUpdate();
                DatabaseHandler.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if(product instanceof Electronic electronic){
            try {
                query = "insert into clothing values (?,?,?,?)";
                PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
                //Electronic electronic = (Electronic) product;
                st.setInt(1,key);
                st.setString(2, electronic.getColor());
                st.setDate(3, java.sql.Date.valueOf(electronic.getWarranty().toString()));
                st.setString(4, electronic.getManualBook());
                st.executeUpdate();
                DatabaseHandler.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if(product instanceof Grocery grocery){
            try {
                query = "insert into clothing values (?,?,?,?)";
                PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
                //Grocery grocery = (Grocery) product;
                st.setInt(1,key);
                st.setDate(2, java.sql.Date.valueOf(grocery.getExpDate().toString()));
                st.setDate(3, java.sql.Date.valueOf(grocery.getProductionDate().toString()));
                st.executeUpdate();
                DatabaseHandler.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
package Controller;

import java.io.File;
import java.sql.*;

import Modul.*;

public class DBController {

    public static boolean updateBook(Book books) {
        Boolean success = false;
        String query = "update product set name = ?, stock = ?, discount = ?, price = ?, photo_product_path = ? where id_product = ?";
        try {
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            st.setString(1, books.getName());
            st.setInt(2, books.getStock());
            st.setDouble(3, books.getDiscount());
            st.setDouble(4, books.getPrice());
            st.setString(5, books.getPhotoProduct());
            st.setString(6, books.getIdProduct());
            if (st.executeUpdate() > 0) {
                success = true;
                query = "UPDATE book set title = ?, author = ?, synopsis = ?, release_date = ?, page_num = ? where id_product = ?";
                st = DatabaseHandler.connect().prepareStatement(query);
                st.setString(1, books.getTitle());
                st.setString(2, books.getAuthor());
                st.setString(3, books.getSynopsis());
                st.setDate(4, java.sql.Date.valueOf(books.getReleaseDate().toString()));
                st.setInt(5, books.getPageNum());
                st.setString(6, books.getIdProduct());
                st.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseHandler.disconnect();
        return success;
    }

    public static boolean updateClothing(Clothing clothing) {
        Boolean success = false;
        String query = "update product set name = ?, stock = ?, discount = ?, price = ?, photo_product_path = ? where id_product = ?";
        try {
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            st.setString(1, clothing.getName());
            st.setInt(2, clothing.getStock());
            st.setDouble(3, clothing.getDiscount());
            st.setDouble(4, clothing.getPrice());
            st.setString(5, clothing.getPhotoProduct());
            st.setString(6, clothing.getIdProduct());
            if (st.executeUpdate() > 0) {
                success = true;
                query = "UPDATE clothing set size = ?, color = ? where id_product = ?";
                st = DatabaseHandler.connect().prepareStatement(query);
                st.setString(1, clothing.getSize().toString());
                st.setString(2, clothing.getColor());
                st.setString(3, clothing.getIdProduct());
                st.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseHandler.disconnect();
        return success;
    }

    public static boolean updateElectronic(Electronic electronic) {
        Boolean success = false;
        String query = "update product set name = ?, stock = ?, discount = ?, price = ?, photo_product_path = ? where id_product = ?";
        try {
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            st.setString(1, electronic.getName());
            st.setInt(2, electronic.getStock());
            st.setDouble(3, electronic.getDiscount());
            st.setDouble(4, electronic.getPrice());
            st.setString(5, electronic.getPhotoProduct());
            st.setString(6, electronic.getIdProduct());
            if (st.executeUpdate() > 0) {
                success = true;
                query = "UPDATE electronic set color = ?, warranty = ?, manual_book = ? where id_product = ?";
                st = DatabaseHandler.connect().prepareStatement(query);
                st.setString(1, electronic.getColor().toString());
                st.setDate(2, java.sql.Date.valueOf(electronic.getWarranty().toString()));
                st.setString(3, electronic.getManualBook());
                st.setString(4, electronic.getIdProduct());
                st.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseHandler.disconnect();
        return success;
    }

    public static boolean updateGrocery(Grocery grocery) {
        Boolean success = false;
        String query = "update product set name = ?, stock = ?, discount = ?, price = ?, photo_product_path = ? where id_product = ?";
        try {
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            st.setString(1, grocery.getName());
            st.setInt(2, grocery.getStock());
            st.setDouble(3, grocery.getDiscount());
            st.setDouble(4, grocery.getPrice());
            st.setString(5, grocery.getPhotoProduct());
            st.setString(6, grocery.getIdProduct());
            if (st.executeUpdate() > 0) {
                success = true;
                query = "UPDATE grocery set exp_date = ?, production_date = ? where id_product = ?";
                st = DatabaseHandler.connect().prepareStatement(query);
                st.setDate(1, java.sql.Date.valueOf(grocery.getExpDate().toString()));
                st.setDate(2, java.sql.Date.valueOf(grocery.getProductionDate().toString()));
                st.setString(3, grocery.getIdProduct());
                st.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseHandler.disconnect();
        return success;
    }
}

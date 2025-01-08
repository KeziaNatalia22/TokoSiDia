package Controller;

import Modul.*;
import java.sql.*;

public class DBController {

    public static int getIDTransaction(String buyer, int idShop) {
        String query = "SELECT id_transaksi FROM transaction WHERE username = ? and id_shop = ?";
        try (PreparedStatement st = DatabaseHandler.connect().prepareStatement(query)) {
            st.setString(1, buyer);
            st.setInt(2, idShop);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_transaksi");
                } else {
                    System.out.println("No transaction found for buyer: " + buyer);
                    return -1;
                }
            }
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    public static int getIDShop(String seller) {
        String query = "SELECT id_shop FROM toko WHERE username = ?";
        try (PreparedStatement st = DatabaseHandler.connect().prepareStatement(query)) {
            st.setString(1, seller);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_shop");
                } else {
                    System.out.println("No shop found for seller: " + seller);
                    return -1;
                }
            }
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
    
    public static int getIDCart(int idShop) {
        String query = "SELECT id_cart FROM cart WHERE username = ? AND id_shop = ?";
        try (PreparedStatement st = DatabaseHandler.connect().prepareStatement(query)) {
            st.setString(1, SingletonManager.getInstance().getUser().getName());
            st.setInt(2, idShop);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_cart");
                } else {
                    System.out.println("No cart found for user: " 
                        + SingletonManager.getInstance().getUser().getName() + ", idShop: " + idShop);
                    return -1;
                }
            }
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }    

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

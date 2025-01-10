package Controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import Modul.Product;

public class UpdateProduct {

    public static void updateProduct(Product product){
        String query = "update product set stock = ?, discount = ?, price = ? where id_product = ?";
        try {
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            st.setInt(1, product.getStock());
            st.setDouble(2, product.getDiscount());
            st.setDouble(3, product.getPrice());
            st.setInt(4, Integer.parseInt(product.getIdProduct()));
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseHandler.disconnect();
    }

}
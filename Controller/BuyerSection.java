package Controller;

import Modul.Book;
import Modul.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class BuyerSection {

    public static ArrayList<Product> searchProduct(String input) {
        ArrayList<Product> searchedProduct = new ArrayList<Product>();
        String query =  "SELECT * FROM product AS p "
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
                        Modul.Book book = new Book(
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
                    }
                    else if (!rs.getString("size").isEmpty()) {
                        
                    }
                    else if (!rs.getString("warranty").isEmpty()) {
                        
                    }
                    else{

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

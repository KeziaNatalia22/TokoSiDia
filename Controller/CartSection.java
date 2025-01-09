package Controller;

import Modul.Book;
import Modul.Clothing;
import Modul.ClothingSize_Enum;
import Modul.Electronic;
import Modul.Grocery;
import Modul.Product;
import Modul.SingletonManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CartSection {
    public static void updateCartDB(Product product, int amount) {
        int idShop = DBController.getIDShop(product.getSellerName());
        int idCart = DBController.getIDCart(idShop);

        String query = "UPDATE cart_detail SET quantity = ? WHERE id_product = ? AND id_cart = ?";
        try (PreparedStatement st = DatabaseHandler.connect().prepareStatement(query)) {
            st.setInt(1, amount);
            st.setInt(2, Integer.parseInt(product.getIdProduct()));
            st.setInt(3, idCart);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean notInCartDetail(Product product, int idCart) {
        boolean statement = true;

        String query = "SELECT 1 FROM cart_detail WHERE id_cart = ? AND id_product = ?";
        try (PreparedStatement st = DatabaseHandler.connect().prepareStatement(query)) {
            st.setInt(1, idCart);
            st.setInt(2, Integer.parseInt(product.getIdProduct()));

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    statement = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            statement = false;
        }

        return statement;
    }

    public static void insertCartDB(Product product, int amount) {
        int idShop = DBController.getIDShop(product.getSellerName());
        int idCart = DBController.getIDCart(idShop);
        String user = SingletonManager.getInstance().getUser().getName();

        try {
            if (idCart == -1) {
                String query1 = "INSERT INTO cart (username, id_shop) VALUES (?, ?)";
                try (PreparedStatement st1 = DatabaseHandler.connect().prepareStatement(query1)) {
                    st1.setString(1, user);
                    st1.setInt(2, idShop);
                    st1.execute();
                }
            }
            else{
                if (notInCartDetail(product, idCart)) {
                    String query2 = "INSERT INTO cart_detail (id_cart, id_product, quantity) VALUES (?, ?, ?)";
                    try (PreparedStatement st2 = DatabaseHandler.connect().prepareStatement(query2)) {
                        st2.setInt(1, idCart);
                        st2.setInt(2, Integer.parseInt(product.getIdProduct()));
                        st2.setInt(3, amount);
                        st2.execute();
                    }
                }
                else{
                    updateCartDB(product, amount);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean removeCartDB(Product product) {
        int idShop = DBController.getIDShop(product.getSellerName());
        int idCart = DBController.getIDCart(idShop);
        int dialogButton = JOptionPane.showConfirmDialog (null, "Apakah yakin?","Remove From Cart",JOptionPane.YES_NO_OPTION);
        if (dialogButton == JOptionPane.YES_OPTION) {
            try {
                if (SingletonManager.getInstance().getCart().get(product.getSellerName()).size() == 1) {
                    String query = "DELETE FROM cart WHERE id_cart = ? AND username = ?";
                    try (PreparedStatement st = DatabaseHandler.connect().prepareStatement(query)) {
                        st.setInt(1, idCart);
                        st.setString(2, SingletonManager.getInstance().getUser().getName());
                        st.executeUpdate();
                    }
                } else {
                    String query = "DELETE FROM cart_detail WHERE id_cart = ? AND id_product = ?";
                    try (PreparedStatement st = DatabaseHandler.connect().prepareStatement(query)) {
                        st.setInt(1, idCart);
                        st.setInt(2, Integer.parseInt(product.getIdProduct()));
                        st.executeUpdate();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public static void getCartFromDB(String username){
        String query = 
            "SELECT c.*, cd.*, p.*, b.*, g.*, cl.*, e.* " +
            "FROM cart c " +
            "RIGHT JOIN cart_detail cd ON c.id_cart = cd.id_cart " +
            "RIGHT JOIN product p ON cd.id_product = p.id_product " +
            "LEFT JOIN book b ON p.id_product = b.id_product " +
            "LEFT JOIN grocery g ON p.id_product = g.id_product " +
            "LEFT JOIN clothing cl ON p.id_product = cl.id_product " +
            "LEFT JOIN electronic e ON p.id_product = e.id_product " +
            "WHERE c.username = ? ";
        try {
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
    
            while (rs.next()) {
                Product product = null;
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
                    product = book;
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
                        product = cloth;
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
                    product = electronic;
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
                        product = grocery;
                }
                BuyerSection.addToCart(product, rs.getInt("quantity"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

package Controller;

import Modul.Book;
import Modul.Clothing;
import Modul.ClothingSize_Enum;
import Modul.Electronic;
import Modul.Grocery;
import Modul.Product;
import Modul.SingletonManager;
import Modul.User;
import View.ShowCart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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

    public static ArrayList<Product> searchBook(String input, int offset) {
        ArrayList<Product> searchedProduct = new ArrayList<Product>();
        String query =  "SELECT p.*, b.* "
                        + "FROM product AS p "
                        + "INNER JOIN book AS b ON b.id_product = p.id_product "
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

    public static ArrayList<Product> searchClothing(String input, int offset) {
        ArrayList<Product> searchedProduct = new ArrayList<Product>();
        String query =  "SELECT p.*, c.* "
                        + "FROM product AS p "
                        + "INNER JOIN clothing AS c ON c.id_product = p.id_product "
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

    public static ArrayList<Product> searchElectronic(String input, int offset) {
        ArrayList<Product> searchedProduct = new ArrayList<Product>();
        String query =  "SELECT p.*, e.* "
                        + "FROM product AS p "
                        + "INNER JOIN electronic AS e ON e.id_product = p.id_product "
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

    public static ArrayList<Product> searchGrocery(String input, int offset) {
        ArrayList<Product> searchedProduct = new ArrayList<Product>();
        String query =  "SELECT p.*, g.* "
                        + "FROM product AS p "
                        + "INNER JOIN grocery AS g ON g.id_product = p.id_product "
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

    public static void addToCart(Product product, int quantity){
        SingletonManager login = SingletonManager.getInstance();
        HashMap<String, HashMap<Product, Integer>> cart = login.getCart();
        String seller = product.getSellerName();
        if (cart.containsKey(seller)) {
            HashMap<Product, Integer> prodInCart = cart.get(seller);
            int quantityBefore = 0;
            for (Map.Entry<Product, Integer> productEntry : prodInCart.entrySet()) {
                Product prod = productEntry.getKey();
                if (prod.getIdProduct().equalsIgnoreCase(product.getIdProduct())) {
                    product = prod;
                    quantityBefore = prodInCart.get(prod);
                    break;
                }
            }
            int quantityAfter = quantityBefore + quantity;
            SingletonManager.getInstance().getCart().get(seller).put(product, quantityAfter);
        }
        else{
            HashMap<Product, Integer> newProd = new HashMap<>();
            newProd.put(product, quantity);
            SingletonManager.getInstance().getCart().put(seller, newProd);
        }
    }

    public static void updateCart(Product product, int quantity){
        String seller = product.getSellerName();
        SingletonManager.getInstance().getCart().get(seller).put(product, quantity);
    }

    public static void removeProductFromCart(Product product){
        SingletonManager login = SingletonManager.getInstance();
        HashMap<String, HashMap<Product, Integer>> cart = login.getCart();
        String seller = product.getSellerName();
        HashMap<Product, Integer> prodInCart = cart.get(seller);
        for (Map.Entry<Product, Integer> productEntry : prodInCart.entrySet()) {
            Product prod = productEntry.getKey();
            if (prod.getIdProduct().equalsIgnoreCase(product.getIdProduct())) {
                if (SingletonManager.getInstance().getCart().get(seller).size() != 1) {
                    SingletonManager.getInstance().getCart().get(seller).remove(prod);
                }
                else{
                    SingletonManager.getInstance().getCart().remove(seller);
                }
                break;
            }
        }
    }

    public static void removeProductCheckout(Product product){
        SingletonManager login = SingletonManager.getInstance();
        HashMap<String, HashMap<Product, Integer>> cart = login.getCart();
        String seller = product.getSellerName();
        HashMap<Product, Integer> prodInCart = cart.get(seller);
        for (Map.Entry<Product, Integer> productEntry : prodInCart.entrySet()) {
            Product prod = productEntry.getKey();
            if (prod.getIdProduct().equalsIgnoreCase(product.getIdProduct())) {
                if (SingletonManager.getInstance().getCart().get(seller).size() != 1) {
                    SingletonManager.getInstance().getCart().get(seller).remove(prod);
                }
                else{
                    SingletonManager.getInstance().getCart().remove(seller);
                }
                break;
            }
        }
    }

    public static void checkout(HashMap<Product, Integer> productList, AtomicInteger totalPrice, int idShop){
        User login = SingletonManager.getInstance().getUser();
        DateTimeFormatter format = DateTimeFormatter.BASIC_ISO_DATE;
        String date = LocalDate.now().format(format);
        int totalPrice2 = totalPrice.get();
        if (login.geteMoney() >= totalPrice2) {
            int dialogButton = JOptionPane.showConfirmDialog (null, "Apakah yakin?","Buy Items",JOptionPane.YES_NO_OPTION);
            if (dialogButton == JOptionPane.YES_OPTION) {
                try {
                    String query1 = "INSERT INTO transaction (username, id_shop, date, shipment_status) VALUES (?, ?, ?, ?)";
                    PreparedStatement st1 = DatabaseHandler.connect().prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
                    st1.setString(1, login.getName());
                    st1.setInt(2, idShop);
                    st1.setString(3, date);
                    st1.setString(4, Modul.ShipmentStatus_Enum.PACKED.toString());
                    st1.execute();

                    ResultSet rs = st1.getGeneratedKeys();
                    rs.next();
                    int idTransaction = rs.getInt(1);

                    for (Map.Entry<Product, Integer> productEntry : productList.entrySet()) {
                        Product product = productEntry.getKey();
                        int amount = productEntry.getValue();
                        String query2 = "INSERT INTO detail_transaction (id_transaksi, id_product, quantity) VALUES (?, ?, ?)";
                        PreparedStatement st2 = DatabaseHandler.connect().prepareStatement(query2);
                        st2.setInt(1, idTransaction);
                        st2.setInt(2, Integer.parseInt(product.getIdProduct()));
                        st2.setInt(3, amount);
                        st2.execute();

                        CartSection.removeCartDB(product);
                        BuyerSection.removeProductCheckout(product);
                    }

                    double balance = (int)login.geteMoney() - totalPrice2;
                    SingletonManager.getInstance().getUser().seteMoney(balance);

                    String query3 = "update user set emoney = ? where username = ?";
                    PreparedStatement st3 = DatabaseHandler.connect().prepareStatement(query3);
                    st3.setDouble(1, SingletonManager.getInstance().getUser().geteMoney());
                    st3.setString(2, login.getName());
                    st3.execute();

                    JOptionPane.showMessageDialog(null, "Pembelian berhasil", "Buying", JOptionPane.INFORMATION_MESSAGE);
                    new ShowCart();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Uang anda tidak cukup, silahkan mengisi balance","Balance", JOptionPane.ERROR_MESSAGE);
        }
    }
}

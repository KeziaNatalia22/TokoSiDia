package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Register {
    public static int checkUniqueUsername(String username){
        String query = "select username from user where username = ?";
        try{
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);

            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return 0;
            }
            else{
                return 1;
            }  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Checking Unique Username", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public static int checkUniqueEmail(String email){
        String query = "select email from user where email = ?";
        try{
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);

            st.setString(1, email);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return 0;
            }
            else{
                return 1;
            }  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Checking Unique Email", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    // public static void inputDatatoDB(String username, String phoneNum, String email, String password, String address, String type){
    //     String query = "INSERT INTO user (username, phone_number, email, passwrd, address, acc_stat, type)" + 
    //                     "VALUES (?, ?, ?, ?, ?, 'ACTIVE', ?)";
    //     try{
    //         PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
    //         st.setString(1, username);
    //         st.setString(2, phoneNum);
    //         st.setString(3, email);
    //         st.setString(4, password);
    //         st.setString(5, address);
    //         st.setString(6, type);

    //         st.execute();

    //         JOptionPane.showMessageDialog(null, "Registrasi Berhasil", "Registrasi", JOptionPane.INFORMATION_MESSAGE);
    //     } catch (Exception e) {
    //         JOptionPane.showMessageDialog(null, "Error in Input Data", "Error", JOptionPane.ERROR_MESSAGE);
    //     }
    // }

    public static void inputDatatoDB(String username, String phoneNum, String email, String password, String address, String type, String shopName, String city){
        try{
            if (type.equalsIgnoreCase("buyer")) {
                String query = "INSERT INTO user (username, phone_number, email, passwrd, address, acc_stat, type)" + 
                        "VALUES (?, ?, ?, ?, ?, 'ACTIVE', ?)";
                PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
                st.setString(1, username);
                st.setString(2, phoneNum);
                st.setString(3, email);
                st.setString(4, password);
                st.setString(5, address);
                st.setString(6, type);
    
                st.execute();
            }
            else{
                String query1 = "INSERT INTO user (username, phone_number, email, passwrd, address, acc_stat, type) " +
                                "VALUES (?, ?, ?, ?, ?, 'ACTIVE', ?);";
                PreparedStatement st1 = DatabaseHandler.connect().prepareStatement(query1);
                st1.setString(1, username);
                st1.setString(2, phoneNum);
                st1.setString(3, email);
                st1.setString(4, password);
                st1.setString(5, address);
                st1.setString(6, type);

                st1.executeUpdate();
                st1.close();

                String query2 = "INSERT INTO toko (username, shop_name, city_located) " +
                                "VALUES (?, ?, ?);";
                PreparedStatement st2 = DatabaseHandler.connect().prepareStatement(query2);
                st2.setString(1, username);
                st2.setString(2, shopName);
                st2.setString(3, city);

                st2.executeUpdate();
                st2.close();
            }

            JOptionPane.showMessageDialog(null, "Registrasi Berhasil", "Registrasi", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Input Data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static boolean updateData(String username, String phoneNum, String email, String address) {
        try {
            String query = "UPDATE user SET phone_number = ?, email = ?, address = ? WHERE username = ?";
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
    
            st.setString(1, phoneNum);
            st.setString(2, email);
            st.setString(3, address);
            st.setString(4, username);
    
            int rowsAffected = st.executeUpdate();
    
            st.close();
    
            if (rowsAffected > 0) {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error updating user data: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false; 
    }
    
}

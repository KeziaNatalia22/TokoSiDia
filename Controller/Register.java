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

    public static void inputDatatoDB(String username, String phoneNum, String email, String password, String address, String type){
        String query = "INSERT INTO user (username, phone_number, email, passwrd, address, acc_stat, type)" + 
                        "VALUES (?, ?, ?, ?, ?, 'ACTIVE', ?)";
        try{
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            st.setString(1, username);
            st.setString(2, phoneNum);
            st.setString(3, email);
            st.setString(4, password);
            st.setString(5, address);
            st.setString(7, type);

            st.execute();

            JOptionPane.showMessageDialog(null, "Registrasi Berhasil", "Registrasi", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Input Data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

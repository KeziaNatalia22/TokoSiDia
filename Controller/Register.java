package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Register {
    public static int checkUniqueUsername(String username){
        String query = "select username from users where username = ?";
        try{
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);

            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            if (rs == null) {
                return 1;
            }
            else{
                return 0;
            }  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Checking Unique Username", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public static int checkUniqueEmail(String email){
        String query = "select email from users where email = ?";
        try{
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);

            st.setString(1, email);

            ResultSet rs = st.executeQuery();

            if (rs == null) {
                return 1;
            }
            else{
                return 0;
            }  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Checking Unique Email", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }
}

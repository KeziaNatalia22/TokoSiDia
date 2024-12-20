package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Login {
    public static void loginTokoSidia(String username, String password) {
        String query = "select * from user where username = ?";
        try {
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                query = "select * from user where username = ? and password = ?";
                st = DatabaseHandler.connect().prepareStatement(query);
                st.setString(1, username);
                st.setString(2, password);

                rs = st.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login Berhasil", "Login", JOptionPane.DEFAULT_OPTION);
                    
                    if (rs.getString("type").equalsIgnoreCase("seller")) {
                        // View.HomeSeller();
                    } else {
                        // View.HomeBuyer();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Login Gagal\nPassword Salah", "Login Gagal",
                            JOptionPane.DEFAULT_OPTION);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Login Gagal\nUsername not found", "Login Gagal",
                        JOptionPane.DEFAULT_OPTION);
                // View.Login();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void forgotPassword() {

    }

}

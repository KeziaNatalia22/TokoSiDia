package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.mail.Message;
import javax.swing.JOptionPane;
import Modul.*;

public class Login {
    public static void loginTokoSidia(String username, String password) {
        String query = "select * from user where username = ?";
        try {
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                if (rs.getString("password").equals(password)) {
                    JOptionPane.showMessageDialog(null, "Login Berhasil", "Login", JOptionPane.DEFAULT_OPTION);

                    if (rs.getString("type").equalsIgnoreCase("seller")) {
                        // View.HomeSeller();
                    } else {
                        Buyer user = new Buyer(
                        rs.getString("username"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("photo_path"),
                        rs.getDouble("emoney"),
                        AccountStatus_Enum.valueOf(rs.getString("acc_stat")),
                        rs.getString("address"));
                        new View.HomeBuyer(user);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Login Gagal\nPassword Salah", "Login Gagal",
                            JOptionPane.DEFAULT_OPTION);
                    new View.Login();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Login Gagal\nUsername not found", "Login Gagal",
                        JOptionPane.DEFAULT_OPTION);
                new View.Login();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
        DatabaseHandler.disconnect();
    }

    public static void forgotPassword(String username, String email) {
        String query = "select * from user where username = ?";
        try {
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            Random rand = new Random();

            if (rs.next()) {
                if (rs.getString("email").equals(email)) {
                    String random = String.format("%06d", rand.nextInt(1000000));
                    GmailSender.sendMail(email, "Tokosidia Forgot Password", "Kode Verifikasi Tokosidia: " + random);
                    String input = JOptionPane.showInputDialog(null, "Check your mail for verification code",
                            "Verification", JOptionPane.DEFAULT_OPTION);
                    if (input.equals(random)) {
                        new View.ChangePassword(username);
                    } else {
                        JOptionPane.showMessageDialog(null, "Kode Verifikasi salah", "Gagal",
                                JOptionPane.DEFAULT_OPTION);
                        new View.Login();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "email tidak sesuai dengan username", "Gagal",
                            JOptionPane.DEFAULT_OPTION);
                    new View.ForgotPassword();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Username not found", "Gagal",
                        JOptionPane.DEFAULT_OPTION);
                new View.ForgotPassword();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
        DatabaseHandler.disconnect();

    }

    public static void changePassword(String username, String password) {
        String query = "update user set password = ? where username = ?";
        try {
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            st.setString(1, password);
            st.setString(2, username);
            st.executeUpdate();
            new View.Login();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
        DatabaseHandler.disconnect();
    }
}

package View;

import java.util.Properties;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuLogin {
    JFrame frame;
    JPanel panel;

    public MenuLogin() {
        Menu();
    }
    public void Menu() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize(); // GET MY SCREEN SIZE

        int screenWidth = screenSize.width; // GET PIXELS FOR WIDTH
        int screenHeight = screenSize.height; // GET PIXELS FOR HEIGHT

        final int FRAME_WIDTH = 300; // SET WIDTH
        final int FRAME_HEIGHT = 400; // SET WEIGHT

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2); // SET START LOCATION FOR X
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2); // SET START LOCATION FOR Y

        frame = new JFrame("Menu"); // CREATE FRAME AND SET TITLE
        
        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT); // SET FRAME BOUND
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        JButton login = new JButton("Login");
        login.setBounds(50, 100, 200, 50);
        login.setFont(new Font("SansSerif", Font.BOLD, 24));
        panel.add(login);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                frame.dispose();
            }
        });
        
        JButton register = new JButton("Register");
        register.setBounds(50, 200, 200, 50);
        register.setFont(new Font("SansSerif", Font.BOLD, 24));
        panel.add(register);

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register();
                frame.dispose();
            }
        });

        JButton forgotPassword = new JButton("ForgotPassword");
        forgotPassword.setBounds(50, 200, 200, 50);
        forgotPassword.setFont(new Font("SansSerif", Font.BOLD, 24));
        panel.add(forgotPassword);

        forgotPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //halaman forgot password
                frame.dispose();
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    JFrame frame;
    JPanel panel;

    public Login() {
        LogIn();
    }

    public void LogIn() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize(); // Get screen size

        int screenWidth = screenSize.width; // Screen width
        int screenHeight = screenSize.height; // Screen height

        final int FRAME_WIDTH = 400; // Set frame width
        final int FRAME_HEIGHT = 500; // Set frame height

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2); // Center frame horizontally
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2); // Center frame vertically

        frame = new JFrame("Login Menu"); // Create frame and set title

        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT); // Set frame bounds
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        panel.setBackground(new Color(240, 248, 255)); // Light blue background

        JLabel titleLabel = new JLabel("Welcome to Tokosidia", SwingConstants.CENTER);
        titleLabel.setBounds(50, 20, 300, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(25, 25, 112)); // Dark blue text
        panel.add(titleLabel);

        JLabel label1 = new JLabel("Username:");
        label1.setBounds(50, 100, 300, 30);
        label1.setFont(new Font("Arial", Font.PLAIN, 14));
        label1.setForeground(Color.BLACK);
        panel.add(label1);

        JTextField textField1 = new JTextField();
        textField1.setBounds(50, 130, 300, 30);
        textField1.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(textField1);

        JLabel label2 = new JLabel("Password:");
        label2.setBounds(50, 180, 300, 30);
        label2.setFont(new Font("Arial", Font.PLAIN, 14));
        label2.setForeground(Color.BLACK);
        panel.add(label2);

        JPasswordField passwordField1 = new JPasswordField();
        passwordField1.setBounds(50, 210, 300, 30);
        passwordField1.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(passwordField1);

        JButton submit = new JButton("Login");
        submit.setBounds(50, 270, 300, 40);
        submit.setFont(new Font("Arial", Font.BOLD, 16));
        submit.setBackground(new Color(100, 149, 237)); 
        submit.setForeground(Color.WHITE);
        submit.setFocusPainted(false);
        panel.add(submit);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call database controller function to check if user is buyer or seller
                // Redirect to the respective page based on user role
                frame.dispose();
            }
        });

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(50, 330, 300, 40);
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setBackground(new Color(60, 179, 113)); // Medium sea green background
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        panel.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register();
                frame.dispose();
            }
        });

        frame.setVisible(true);
        frame.add(panel);
    }
}

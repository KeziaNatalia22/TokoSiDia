package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgotPassword {
    JFrame frame;
    JPanel panel;

    public ForgotPassword() {
        forgotPassword();
    }

    public void forgotPassword(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize(); // Get screen size

        int screenWidth = screenSize.width; // Screen width
        int screenHeight = screenSize.height; // Screen height

        final int FRAME_WIDTH = 400; // Set frame width
        final int FRAME_HEIGHT = 440; // Set frame height

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2); // Center frame horizontally
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2); // Center frame vertically

        frame = new JFrame("Forgot Password"); // Create frame and set title

        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT); // Set frame bounds
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        panel.setBackground(new Color(240, 248, 255)); // Light blue background

        JLabel titleLabel = new JLabel("Forgot Password", SwingConstants.CENTER);
        titleLabel.setBounds(50, 20, 300, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(25, 25, 112)); // Dark blue text
        panel.add(titleLabel);

        JLabel usnL = new JLabel("Username:");
        usnL.setBounds(50, 100, 300, 30);
        usnL.setFont(new Font("Arial", Font.PLAIN, 14));
        usnL.setForeground(Color.BLACK);
        panel.add(usnL);

        JTextField username = new JTextField();
        username.setBounds(50, 130, 300, 30);
        username.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(username);

        JLabel emailL = new JLabel("Email:");
        emailL.setBounds(50, 180, 300, 30);
        emailL.setFont(new Font("Arial", Font.PLAIN, 14));
        emailL.setForeground(Color.BLACK);
        panel.add(emailL);

        JTextField email = new JTextField();
        email.setBounds(50, 210, 300, 30);
        email.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(email);

        JButton submit = new JButton("Submit");
        submit.setBounds(50, 270, 300, 40);
        submit.setFont(new Font("Arial", Font.BOLD, 16));
        submit.setBackground(new Color(100, 149, 237));
        submit.setForeground(Color.WHITE);
        submit.setFocusPainted(false);
        panel.add(submit);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.Login.forgotPassword(username.getText(), email.getText());
                frame.dispose();
            }
        });


        JButton backButton = new JButton("Back");
        backButton.setBounds(50, 330, 300, 40);
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(220, 20, 60)); 
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        panel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                frame.dispose();
            }
        });

        frame.setVisible(true);
        frame.add(panel);

    }
    
}

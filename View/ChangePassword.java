package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ChangePassword {
    JFrame frame;
    JPanel panel;

    public ChangePassword(String username) {
        changePassword(username);
    }

    public void changePassword(String username){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize(); // Get screen size

        int screenWidth = screenSize.width; // Screen width
        int screenHeight = screenSize.height; // Screen height

        final int FRAME_WIDTH = 400; // Set frame width
        final int FRAME_HEIGHT = 440; // Set frame height

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2); // Center frame horizontally
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2); // Center frame vertically

        frame = new JFrame("Change Password"); // Create frame and set title

        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT); // Set frame bounds
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        panel.setBackground(new Color(240, 248, 255)); // Light blue background

        JLabel titleLabel = new JLabel("Change Password", SwingConstants.CENTER);
        titleLabel.setBounds(50, 20, 300, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(25, 25, 112)); // Dark blue text
        panel.add(titleLabel);

        JLabel lPass = new JLabel("New Password:");
        lPass.setBounds(50, 100, 300, 30);
        lPass.setFont(new Font("Arial", Font.PLAIN, 14));
        lPass.setForeground(Color.BLACK);
        panel.add(lPass);

        JPasswordField nPass = new JPasswordField();
        nPass.setBounds(50, 130, 300, 30);
        nPass.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(nPass);

        JButton submit = new JButton("Change");
        submit.setBounds(50, 180, 300, 40);
        submit.setFont(new Font("Arial", Font.BOLD, 16));
        submit.setBackground(new Color(100, 149, 237));
        submit.setForeground(Color.WHITE);
        submit.setFocusPainted(false);
        panel.add(submit);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.Login.changePassword(username, new String(nPass.getPassword()));
                frame.dispose();
            }
        });


        frame.setVisible(true);
        frame.add(panel);

    }
    
}

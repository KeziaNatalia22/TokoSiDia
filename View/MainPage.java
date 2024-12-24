package View;

import javax.swing.*;
import java.awt.*;

public class MainPage {
    JFrame frame;
    JPanel panel;

    public MainPage() {
        Main();
    }

    public void Main() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize(); // Get screen size

        int screenWidth = screenSize.width; // Screen width
        int screenHeight = screenSize.height; // Screen height

        final int FRAME_WIDTH = 800; // Set frame width
        final int FRAME_HEIGHT = 700; // Set frame height

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2); // Center frame horizontally
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2); // Center frame vertically

        frame = new JFrame("Home"); // Create frame and set title

        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT); // Set frame bounds
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        JLabel mainLabel = new JLabel("TokosiDia");
        mainLabel.setFont(new Font("Montserrat", Font.BOLD, 20));
        mainLabel.setBounds(20, 20, 100, 30);
        mainLabel.setForeground(Color.green);
        panel.add(mainLabel);

        JTextArea mainContent = new JTextArea();
        mainContent.setFont(new Font("Arial", Font.PLAIN, 16));
        mainContent.setBounds(140, 20, 400, 30);
        panel.add(mainContent);

        JButton exploreButton = new JButton("Search");
        exploreButton.setFont(new Font("Arial", Font.BOLD, 16));
        exploreButton.setBackground(new Color(100, 149, 237));
        exploreButton.setForeground(Color.WHITE);
        exploreButton.setFocusPainted(false);
        exploreButton.setBounds(540, 20, 100, 30);
        panel.add(exploreButton);

        frame.add(panel);
        frame.setVisible(true);
    }


}

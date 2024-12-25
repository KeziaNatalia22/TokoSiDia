package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modul.Buyer;

public class HomeBuyer {
    JFrame frame;
    JPanel panel;

    public HomeBuyer(Buyer user) {
        initialize(user);
    }

    public void initialize(Buyer user) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        final int FRAME_WIDTH = 900;
        final int FRAME_HEIGHT = 700;

        int startX = screenWidth / 2 - (FRAME_WIDTH / 2);
        int startY = screenHeight / 2 - (FRAME_HEIGHT / 2);

        frame = new JFrame("Home");
        frame.setBounds(startX, startY, FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        JLabel mainLabel = new JLabel("TokosiDia");
        mainLabel.setFont(new Font("Montserrat", Font.BOLD, 20));
        mainLabel.setBounds(20, 20, 100, 40);
        mainLabel.setForeground(Color.GREEN);
        panel.add(mainLabel);

        JTextArea mainContent = new JTextArea();
        mainContent.setFont(new Font("Arial", Font.PLAIN, 16));
        mainContent.setBounds(140, 20, 400, 40);
        mainContent.setBackground(Color.gray);
        mainContent.setForeground(Color.WHITE);
        panel.add(mainContent);

        JButton exploreButton = new JButton("Search");
        exploreButton.setFont(new Font("Arial", Font.BOLD, 16));
        exploreButton.setBackground(new Color(100, 149, 237));
        exploreButton.setForeground(Color.WHITE);
        exploreButton.setFocusPainted(false);
        exploreButton.setBounds(560, 20, 100, 40);
        panel.add(exploreButton);

        JButton cartButton = new JButton("Cart");
        cartButton.setFont(new Font("Arial", Font.BOLD, 16));
        cartButton.setBackground(new Color(255, 165, 0));
        cartButton.setForeground(Color.WHITE);
        cartButton.setFocusPainted(false);
        cartButton.setBounds(670, 20, 100, 40); 
        panel.add(cartButton);

        JButton profile = new JButton();
        profile.setBounds(780, 20, 80, 80);
        panel.add(profile);

        String imagePath = user.getPhotoPath();
        ImageIcon profileIcon = new ImageIcon(imagePath);

        Image image = profileIcon.getImage().getScaledInstance(profile.getWidth(), profile.getHeight(),
                Image.SCALE_SMOOTH);
        profile.setIcon(new ImageIcon(image));

        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Profile(user);
            }
        });

        cartButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Menu Keranjang belum diimplementasikan!");
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}

package View;

import javax.swing.*;
import java.awt.*;
import Modul.Buyer;

public class HomeBuyer {
    private JFrame frame;

    public HomeBuyer(Buyer user) {
        initialize(user);
    }

    private void initialize(Buyer user) {
        // Setup frame
        frame = new JFrame("Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 15, 10, 15));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        leftPanel.setBackground(Color.WHITE);

        JLabel mainLabel = new JLabel("TokosiDia");
        mainLabel.setFont(new Font("Montserrat", Font.BOLD, 20));
        mainLabel.setForeground(Color.GREEN);
        leftPanel.add(mainLabel);

        JTextField searchField = new JTextField(25);
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));
        leftPanel.add(searchField);

        JButton searchButton = createButton("Search", new Color(100, 149, 237));
        leftPanel.add(searchButton);

        JButton cartButton = createIconButton("Photos/cart.jpeg", 40, 40, new Color(255, 165, 0));
        leftPanel.add(cartButton);

        headerPanel.add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setBackground(Color.WHITE);

        JLabel balanceLabel = new JLabel("Balance: Rp." + user.geteMoney());
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        balanceLabel.setForeground(Color.BLACK);
        rightPanel.add(balanceLabel);

        JButton profileButton = createIconButton(user.getPhotoPath(), 60, 60, Color.WHITE);
        rightPanel.add(profileButton);

        headerPanel.add(rightPanel, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        profileButton.addActionListener(e -> {
            frame.dispose();
            new Profile(user); 
        });

        cartButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Menu Keranjang belum diimplementasikan!");
        });

        searchButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Search functionality belum diimplementasikan!");
        });

        frame.setVisible(true);
    }

    private JButton createButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    private JButton createIconButton(String imagePath, int width, int height, Color backgroundColor) {
        JButton button = new JButton();
        button.setBackground(backgroundColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(img));
        button.setPreferredSize(new Dimension(width, height));
        return button;
    }
}
